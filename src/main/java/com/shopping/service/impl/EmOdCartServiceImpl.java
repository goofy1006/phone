package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.shopping.common.Constants;
import com.shopping.dao.*;
import com.shopping.model.*;
import com.shopping.model.jsonbean.AmountJsonBean;
import com.shopping.model.jsonbean.EmOdCartJsonBean;
import com.shopping.service.IEmOdCartService;
import com.shopping.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2018/7/30.
 */
@Service
public class EmOdCartServiceImpl implements IEmOdCartService {

    private Logger logger = LoggerFactory.getLogger(EmOdCartServiceImpl.class);

    @Autowired
    EmCmContractGoodsMapperExt emCmContractGoodsMapperExt;
    @Autowired
    EmOdCartMapperExt emOdCartMapperExt;
    @Autowired
    EmOdOrdersMapperExt emOdOrdersMapperExt;
    @Autowired
    EmOdOrderDetailMapperExt emOdOrderDetailMapperExt;
    @Autowired
    private TicketService ticketService;
    @Autowired
    EmTiUserTicketMapperExt emTiUserTicketMapperExt;

    @Autowired
    private EmCmAddressMapperExt emCmAddressMapperExt;

    @Autowired
    private EmBbRegionMapperExt emBbRegionMapperExt;

    @Autowired
    private EmGdGoodsMapperExt emGdGoodsMapperExt;

    @Override
    public APIResponse<List<EmOdCartJsonBean>> selectListCart(String customerId,String goodsId) {
        Map<String,Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("goodsId",goodsId);
        List<EmOdCartJsonBean> list = emOdCartMapperExt.selectListCart(map);
        return APIResponse.success(list);
    }

    @Override
    public APIResponse insertCart(String contractGoodsId,String customerId) {
        EmOdCart emOdCartQ = new EmOdCart();
        emOdCartQ.setGoodsId(contractGoodsId);
        emOdCartQ.setCustomerId(customerId);
        List<EmOdCart> list = emOdCartMapperExt.selectList(emOdCartQ);
        if(list != null && !list.isEmpty()){//不为空，数量+1
            emOdCartQ.setCount(list.get(0).getCount().add(new BigDecimal(1)));
            emOdCartQ.setCartId(list.get(0).getCartId());
            emOdCartMapperExt.updateByPrimaryKeySelective(emOdCartQ);
        }else{//为空插入
            EmOdCart emOdCart = new EmOdCart();
            EmCmContractGoods emCmContractGoods = emCmContractGoodsMapperExt.selectByPrimaryKey(contractGoodsId);
            emOdCart.setCartId(CommonUtil.getUUID());
            emOdCart.setCustomerId(customerId);
            emOdCart.setGoodsId(emCmContractGoods.getContractGoodsId());
            emOdCart.setGoodsName(emCmContractGoods.getContractGoodsName());
            emOdCart.setStoreId("店铺ID");
            emOdCart.setStoreName("店铺名称");
            emOdCart.setCount(new BigDecimal(1));
            emOdCart.setCreatedTime(new Date());
            emOdCart.setCreatedBy(customerId);
            emOdCartMapperExt.insertSelective(emOdCart);
        }
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse deleGoodsCart(String cartId) {
        emOdCartMapperExt.deleteByPrimaryKey(cartId);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse editCartGoodsNum(String cartId, Integer num) {
        Map<String,Object> map = new HashMap<>();
        map.put("cartId",cartId);
        map.put("num",num);
        emOdCartMapperExt.editCartGoodsNum(map);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse insertOrders(String[] ordersArrs,String addressId,String customerId,String ticketId,BigDecimal totalAmount,BigDecimal actualAmount,BigDecimal discountAmpunt,Integer[] numbers,String userTicketId) {
        logger.info("ticketId:"+ticketId+"totalAmount:"+totalAmount+"actualAmount:"+actualAmount+"discountAmpunt:"+discountAmpunt);
        String orderId = CommonUtil.getUUID();
        String randomNum = "";
        for(int i = 0;i<10;i++){
            randomNum += (int)(10*(Math.random()));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = format.format(new Date())+ randomNum.substring(0,8);
        if(!StringUtils.isEmpty(ticketId)){
            APIResponse<AmountJsonBean> apiResponse = ticketService.calculatePrice(ordersArrs, numbers, customerId, ticketId);
            if(actualAmount.compareTo(apiResponse.getData().getActualAmount()) != 0){
                    return APIResponse.fail("订单金额错误，请重试");
            }
            //跟新用户优惠券为已使用，抵扣的金额
            EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
            emTiUserTicket.setId(userTicketId);
            emTiUserTicket.setIsUse(Constants.TicketIsUse.HAD_USED);
            emTiUserTicket.setDeductionAmt(apiResponse.getData().getDiscountAmpunt());
            emTiUserTicket.setOrderNo(orderNo);
            emTiUserTicketMapperExt.updateByPrimaryKeySelective(emTiUserTicket);
            actualAmount = apiResponse.getData().getActualAmount();
            logger.info("totalAmount:"+apiResponse.getData().getTotalAmount()+"actualAmount:"+apiResponse.getData().getActualAmount()+"discountAmpunt:"+apiResponse.getData().getDiscountAmpunt());
        }
        //String [] orderAt = ordersArrs.split(",");
        String [] orderAt = ordersArrs;
        BigDecimal orderAmt = new BigDecimal(0);
        for(int  i= 0;i<orderAt.length;i++){
            EmCmContractGoods emCmContractGoods = emCmContractGoodsMapperExt.selectByPrimaryKey(orderAt[i]);
            EmGdGoods emGdGoods = emGdGoodsMapperExt.selectByPrimaryKey(emCmContractGoods.getGoodsId());
            EmOdCart emOdCart = new EmOdCart();
            emOdCart.setCustomerId(customerId);
            emOdCart.setGoodsId(orderAt[i]);
            List<EmOdCart> list = emOdCartMapperExt.selectList(emOdCart);
            EmOdOrderDetail emOdOrderDetail = new EmOdOrderDetail();
            emOdOrderDetail.setOrderDetailId(CommonUtil.getUUID());//订单明细ID
            emOdOrderDetail.setOrderId(orderId);//订单ID
            emOdOrderDetail.setGoodsId(emCmContractGoods.getContractGoodsId());//商品ID
            emOdOrderDetail.setGoodsName(emCmContractGoods.getContractGoodsName());//商品名称
            emOdOrderDetail.setCount(list.get(0).getCount());//商品数量
            emOdOrderDetail.setUnit(emGdGoods.getUnit());//商品单位
            emOdOrderDetail.setSalesPrice(emCmContractGoods.getContractPrice());//销售价格
            emOdOrderDetail.setAmount(emCmContractGoods.getContractPrice().multiply(list.get(0).getCount()));//总价格
            emOdOrderDetail.setCreatedBy(customerId);
            emOdOrderDetail.setCreatedTime(new Date());
            emOdOrderDetail.setPriceB(emGdGoods.getPriceB());//B价格
            orderAmt = orderAmt.add(emCmContractGoods.getContractPrice().multiply(list.get(0).getCount()));//获取订单总金额

            emOdOrderDetail.setPriceA(emGdGoods.getPriceA());//成本价
            emOdOrderDetail.setPriceC(emGdGoods.getPriceC());//出厂价

            emOdOrderDetailMapperExt.insertSelective(emOdOrderDetail);//插入商品明细表
            emOdCartMapperExt.deleteByPrimaryKey(list.get(0).getCartId());//删除购物车商品
        }
        if(actualAmount == null){//为空时实际支付金额为总金额
            actualAmount = orderAmt;
        }
        insertOrder(orderId,addressId,customerId,orderAmt,actualAmount,orderNo);//插入商品主表
        return APIResponse.success(APIResponse.SUCCESS);
    }

    /**
     * 生成订单
     * @param addressId
     * @param customerId
     * @return
     */
    private String insertOrder(String orderId,String addressId,String customerId,BigDecimal orderAmt,BigDecimal actualAmount,String orderNo){
        EmOdOrders emOdOrders = new EmOdOrders();
        emOdOrders.setOrderId(orderId);//订单ID
        emOdOrders.setContractCustomerId("pingtai1");//合约客户ID定死
        emOdOrders.setCustomerId(customerId);//用户ID
        emOdOrders.setOrderNo(orderNo);//商品编码
        emOdOrders.setStoreId("1");
        emOdOrders.setStoreName("福正商城");
        emOdOrders.setCostCenterId("");//
        emOdOrders.setOrderType(10100005);//订单类型 普通订单
        emOdOrders.setOrderStatus(10110005);//订单状态 10110005:代付款  10110010 ：待发货 10110015：待签收  10110040：已完成
        emOdOrders.setOrderAmt(orderAmt);//订单总金额
        emOdOrders.setAddressId(addressId);//收货地址ID
        EmCmAddress emCmAddress = emCmAddressMapperExt.selectByPrimaryKey(addressId);
        List<EmBbRegion> emBbRegionList = emBbRegionMapperExt.selectList(new EmBbRegion());
        Map<String, String> regionMap = new HashMap<>();
        for(EmBbRegion emBbRegion : emBbRegionList){
            regionMap.put(emBbRegion.getRegionId(), emBbRegion.getRegionName());
        }
        if(emCmAddress != null){
            emOdOrders.setAddress(regionMap.get(emCmAddress.getCounty()).concat(regionMap.get(emCmAddress.getProvince())).concat(regionMap.get(emCmAddress.getCity())).concat(regionMap.get(emCmAddress.getCountry())).concat(emCmAddress.getAddress()));
            emOdOrders.setLinkman(emCmAddress.getLinkman());
            emOdOrders.setTelephone(emCmAddress.getTelephone());
            emOdOrders.setLinkphone(emCmAddress.getLinkphone());
        }
        emOdOrders.setDeliveryAmt(new BigDecimal(0));
        emOdOrders.setLadingWay(10120010);
        emOdOrders.setPayWay(10130025);
        emOdOrders.setDeliveryPeriod(10200005);
        emOdOrders.setUpdatedBy(customerId);
        emOdOrders.setUpdatedTime(new Date());
        emOdOrders.setVersion(Long.valueOf(1));
        emOdOrders.setInvoiceAmountNotyet(actualAmount);
        emOdOrders.setInvoiceAmountNotyet(actualAmount);//未开票金额
        emOdOrders.setInvoiceAmount(new BigDecimal(0));


        emOdOrders.setOrderDate(new Date());//订单时间
        emOdOrders.setIsAudited((short) 0);//是否需要审核
        emOdOrders.setCreatedBy(customerId);//创建人
        emOdOrders.setCreatedTime(new Date());//创建时间

        //emOdOrders.setSupplierId("1111111");//供应商编号
        //emOdOrders.setSupplierName("供应商名称");//供应商名称
        emOdOrders.setPayType("0");//支付方式
        emOdOrders.setPayFlag("0");//支付状态：待支付
        emOdOrders.setIsPingtai(0);//是否平台
        //emOdOrders.setOrderRemark("手机订单");
        emOdOrders.setActualAmt(actualAmount);//订单实际支付金额
        emOdOrdersMapperExt.insertSelective(emOdOrders);//生成订单
        return orderId;
    }
}
