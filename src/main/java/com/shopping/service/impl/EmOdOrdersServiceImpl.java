package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.common.ERPUtils;
import com.shopping.dao.*;
import com.shopping.model.*;
import com.shopping.model.jsonbean.*;
import com.shopping.service.EmOdOrdersService;
import com.shopping.service.IEmCmMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangas on 2018/7/31.
 */
@Service
public class EmOdOrdersServiceImpl implements EmOdOrdersService {

    private static Logger logger = LoggerFactory.getLogger(EmOdOrdersServiceImpl.class);

    @Value("${serviceId}")
    private String serviceId;
    @Value("${key}")
    private String key;
    @Value("${payType}")
    private String payType;
    @Value("${payUrl}")
    private String payUrl;
    @Value("${backup.url}")
    private String backUpUrl;


    @Autowired
    private EmOdOrdersMapperExt emOdOrdersMapperExt;

    @Autowired
    private EmOdOrderDetailMapperExt emOdOrderDetailMapperExt;
    @Autowired
    private EmTiTicketMapperExt emTiTicketMapperExt;
    @Autowired
    private EmTiUserTicketMapperExt emTiUserTicketMapperExt;

    @Autowired
    private EmSysDictionaryMapperExt emSysDictionaryMapperExt;
    @Autowired
    private IEmCmMessageService iEmCmMessageService;
    @Autowired
    PhoneUserMapperExt phoneUserMapperExt;

    @Override
    public APIResponse<List<EmOdOrdersJsonBean>> selectOrdersByStatus(String customerId, Integer orderStatus, Integer pageSize, Integer pageNum) {
        APIResponse<List<EmOdOrdersJsonBean>> result = new APIResponse<>();
        PageHelper.startPage(pageNum, pageSize,"created_time desc");

        EmOdOrders emOdOrders = new EmOdOrders();
        emOdOrders.setCustomerId(customerId);
        emOdOrders.setOrderStatus(orderStatus);

        List<EmOdOrdersJsonBean> jsonBeanList = emOdOrdersMapperExt.selectListByStatus(emOdOrders);
        if(!CollectionUtils.isEmpty(jsonBeanList)){
            for(EmOdOrdersJsonBean emOdOrdersJsonBean : jsonBeanList){
                String orderId = emOdOrdersJsonBean.getOrderId();
                List<EmOdOrderDetailJsonBean> orderDetailList = emOdOrderDetailMapperExt.selectByOrderId(orderId);
                emOdOrdersJsonBean.setEmOdOrderDetailJsonBeanList(orderDetailList);
            }
        }
        PageInfo<EmOdOrdersJsonBean> pageInfo = new PageInfo<>(jsonBeanList);
        result.setCode(APIResponse.SUCCESS);
        result.setData(jsonBeanList);
        result.setTotal((int)pageInfo.getTotal());
        return result;
    }

    @Override
    public APIResponse delOrders(String orderId) {
        EmOdOrderDetail emOdOrderDetail = new EmOdOrderDetail();
        emOdOrderDetail.setOrderId(orderId);
        List<EmOdOrderDetail> list = emOdOrderDetailMapperExt.selectList(emOdOrderDetail);
        //更新优惠券状态为未使用
        List<String> userTickIdList = emTiUserTicketMapperExt.selectTickUserId(orderId);
        if(!CollectionUtils.isEmpty(userTickIdList)){
            EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
            emTiUserTicket.setId(userTickIdList.get(0));
            emTiUserTicket.setIsUse(1);//未使用
            emTiUserTicketMapperExt.updateByPrimaryKeySelective(emTiUserTicket);
        }
        for(EmOdOrderDetail item:list){
            emOdOrderDetailMapperExt.deleteByPrimaryKey(item.getOrderDetailId());
        }
        emOdOrdersMapperExt.deleteByPrimaryKey(orderId);

        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse suOrders(String orderId) {
        EmOdOrders emOdOrders = new EmOdOrders();
        emOdOrders.setOrderId(orderId);
        emOdOrders.setOrderStatus(10110040);//修改状态为已完成
        emOdOrders.setUpdatedTime(new Date());
        emOdOrdersMapperExt.updateByPrimaryKeySelective(emOdOrders);
        try {
            EmOdOrders emOdOrders1 = emOdOrdersMapperExt.selectByPrimaryKey(orderId);
            iEmCmMessageService.insertEmCmMessage(emOdOrders1.getCustomerId(),"订单确认收货消息","您的订单："+emOdOrders1.getOrderNo()+"已确认收货");
        }catch (Exception e){
            logger.error("订单确认收货消息插入失败"+e.getMessage());
        }
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse<PayModelJsonBean> payOrder(String orderId) {
        EmOdOrders emOdOrders = emOdOrdersMapperExt.selectByPrimaryKey(orderId);
        String customerId = emOdOrders.getCustomerId();
        PhoneUser phoneUser = phoneUserMapperExt.selectByPrimaryKey(customerId);
        Map<String,Object> map = new TreeMap<>();
        String orderNo = emOdOrders.getOrderNo();
        String body = "普通商品";
        String totalFee = emOdOrders.getActualAmt().multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_DOWN).toString();
        String timeoutExpire = "24h";
        String createIp = "127.0.0.1";
        map.put("service_id",serviceId);
        map.put("order_no",orderNo);//商品订单
        map.put("body",body);//商品描述
        map.put("total_fee",totalFee);//商品金额
        map.put("pay_type",payType);//支付方式;
        map.put("openId",phoneUser.getOpenId());//openId
        map.put("timeout_expire",timeoutExpire);//超时时间
        String returnUrl = backUpUrl+"/payback/payReturnUrl_"+emOdOrders.getCustomerId()+".do";
        String notifyUrl = backUpUrl+"/payback/payOrderBack.do";
        logger.info("========returnUrl========="+returnUrl+"================");
        map.put("return_url", returnUrl);//同步返回地址
        map.put("create_ip",createIp);//终端IP
        map.put("notify_url",notifyUrl);//异步通知地址
        String sign = com.shopping.common.CommonUtil.generateSign(map, key);
        map.put("sign",sign );
        PayModelJsonBean payModelJsonBean = new PayModelJsonBean();
        payModelJsonBean.setService_id(serviceId);
        payModelJsonBean.setOrder_no(orderNo);
        payModelJsonBean.setBody(body);
        payModelJsonBean.setTotal_fee(totalFee);
        payModelJsonBean.setPay_type(payType);
        payModelJsonBean.setTimeout_expire(timeoutExpire);
        payModelJsonBean.setReturn_url(returnUrl);
        payModelJsonBean.setNotify_url(notifyUrl);
        payModelJsonBean.setSign(sign);
        payModelJsonBean.setPayUrl(payUrl);
        payModelJsonBean.setCreate_ip(createIp);
        payModelJsonBean.setOpenId(phoneUser.getOpenId());
        return APIResponse.success(payModelJsonBean);
    }

    @Override
    @Transactional
    public PayBackResultJsonBean payOrderBack(String payReuslt) {
        logger.error("==========>payReuslt"+payReuslt);
        logger.info("=======支付回调接口======"+payReuslt);
        JSONObject jsonObject = JSONObject.parseObject(payReuslt);
        PayModelBackJsonBean payModelBackJsonBean = (PayModelBackJsonBean)JSONObject.toJavaObject(jsonObject,PayModelBackJsonBean.class);
        //更新优惠券金额，和使用数量
        EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
        emTiUserTicket.setOrderNo(payModelBackJsonBean.getOrder_no());
        //查询订单对应的tickid
        List<EmTiUserTicket> userTicketsList = emTiUserTicketMapperExt.selectList(emTiUserTicket);
        if(!CollectionUtils.isEmpty(userTicketsList)){
            EmTiTicket emTiTicket = new EmTiTicket();
            emTiTicket.setTicketId(userTicketsList.get(0).getTicketId());
            emTiTicket.setAmt(new BigDecimal(payModelBackJsonBean.getTotal_fee()));
            //更新优惠券金额和已使用数量
            emTiTicketMapperExt.updateTicketMonNum(emTiTicket);
            try {
                iEmCmMessageService.insertEmCmMessage(userTicketsList.get(0).getUserId(),"订单支付消息","您的订单："+userTicketsList.get(0).getOrderNo()+"支付成功");
            }catch (Exception e){
                logger.error("订单支付回调消息插入失败"+e.getMessage());
            }
        }
        //更新商品销售数量
        List<EmOdOrderDetailJsonBean> goodsIdList = emOdOrderDetailMapperExt.goodsIdList(payModelBackJsonBean.getOrder_no());
        for(EmOdOrderDetailJsonBean item:goodsIdList){
            emOdOrderDetailMapperExt.updateGoodsSalesVol(item.getGoodsId());
        }
        //EmOdOrders emOdOrders1 = new EmOdOrders();
        //emOdOrders1.setOrderNo(payModelBackJsonBean.getOrder_no());
        //List<EmOdOrders> list = emOdOrdersMapperExt.selectList(emOdOrders1);
        //List<EmOdOrderDetailJsonBean> orderIdList = emOdOrderDetailMapperExt.selectByOrderId(list.get(0).getOrderId());
        //if(!list.get(0).getOrderStatus().equals(10110040)){//已完成
            EmOdOrders emOdOrders = new EmOdOrders();
            if(payModelBackJsonBean.getPay_status().equals("SUCCESS")){
                emOdOrders.setOrderNo(payModelBackJsonBean.getOrder_no());//订单号
                emOdOrders.setOrderStatus(10110010);//待发货
                emOdOrders.setPayFlag("1");//已支付
                emOdOrders.setUpdatedTime(new Date());//更新时间
                emOdOrders.setPayTime(new Date());//支付时间
                emOdOrders.setIsPayback((short)1);//是否已回款
                emOdOrdersMapperExt.updateOrderStatus(emOdOrders);

            }
        //}
        PayBackResultJsonBean payBackResultJsonBean = new PayBackResultJsonBean();
        payBackResultJsonBean.setReturn_code("SUCCESS");
        return payBackResultJsonBean;
    }

    @Override
    public APIResponse orderDescribe(String orderNo) {

        List<OrderDetailJsonBean> orderDetailJsonBeanList = emOdOrdersMapperExt.selectOrderByOrderNo(orderNo);
        if(!CollectionUtils.isEmpty(orderDetailJsonBeanList)){
            for(OrderDetailJsonBean orderDetailJsonBean : orderDetailJsonBeanList){
                if(orderDetailJsonBean.getCarrierName() != null){
                    EmSysDictionary emSysDictionary = new EmSysDictionary();
                    emSysDictionary.setDictKey(Integer.valueOf(orderDetailJsonBean.getCarrierName()));
                    List<EmSysDictionary> emSysDictionaries = emSysDictionaryMapperExt.selectList(emSysDictionary);
                    orderDetailJsonBean.setCarrierName(emSysDictionaries.get(0).getDictValue());
                }
            }
        }

        return APIResponse.success(orderDetailJsonBeanList);
    }
}
