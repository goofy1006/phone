package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import cn.zjmiec.xks.redis.DefaultRedisService;
import com.shopping.common.Constants;
import com.shopping.common.DateUtil;
import com.shopping.dao.EmCmContractGoodsMapperExt;
import com.shopping.dao.EmTiTicketCatetgoryMapperExt;
import com.shopping.dao.EmTiTicketMapperExt;
import com.shopping.dao.EmTiUserTicketMapperExt;
import com.shopping.model.EmCmContractGoods;
import com.shopping.model.EmTiTicket;
import com.shopping.model.EmTiTicketCatetgory;
import com.shopping.model.EmTiUserTicket;
import com.shopping.model.jsonbean.AmountJsonBean;
import com.shopping.model.jsonbean.SessionManagerJsonBean;
import com.shopping.model.jsonbean.TicketJsonBean;
import com.shopping.model.jsonbean.TicketListJsonBean;
import com.shopping.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangas on 2018/8/11.
 */
@Service
public class TicketServiceImpl implements TicketService{

    private static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private EmTiUserTicketMapperExt emTiUserTicketMapperExt;

    @Autowired
    private EmTiTicketMapperExt emTiTicketMapperExt;

    @Autowired
    private DefaultRedisService defaultRedisService;

    @Autowired
    private EmCmContractGoodsMapperExt emCmContractGoodsMapperExt;

    @Autowired
    private EmTiTicketCatetgoryMapperExt emTiTicketCatetgoryMapperExt;

    @Override
    public APIResponse<List<TicketJsonBean>> ticketList(TicketJsonBean ticketJsonBean) {
        List<TicketJsonBean> ticketJsonBeans = emTiUserTicketMapperExt.ticketList(ticketJsonBean);
        if(!CollectionUtils.isEmpty(ticketJsonBeans)){
            for(TicketJsonBean jsonBean : ticketJsonBeans){
                //根据ticketId查询品类名称
                List<String> list = emTiUserTicketMapperExt.selectCategoryNameByticketId(jsonBean.getTicketId());
                StringBuffer sb = new StringBuffer();
                if(!CollectionUtils.isEmpty(list)){
                    for(String item:list){
                        sb.append(item).append(",");
                    }
                }
                jsonBean.setCategoryNameAttrs(sb.toString());
                Date efendTime = jsonBean.getEfendTime();
                if(System.currentTimeMillis() > DateUtil.getEndDateTimeOfDay(efendTime).getTime()){
                    //暂时使用魔法值
                    //代表已过期
                    jsonBean.setIsUse(Constants.TicketIsUse.BE_OVERDUE);
                }
            }
        }
        return APIResponse.success(ticketJsonBeans);
    }

    @Override
    public APIResponse<List<TicketListJsonBean>> allTicketList(TicketListJsonBean ticketListJsonBean) {
        String userId = ticketListJsonBean.getUserId();
        EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
        emTiUserTicket.setUserId(userId);
        List<EmTiUserTicket> emTiUserTicketList = emTiUserTicketMapperExt.selectList(emTiUserTicket);
        Map<String, EmTiUserTicket> userTicketMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(emTiUserTicketList)){
            for(EmTiUserTicket userTicket : emTiUserTicketList){
                userTicketMap.put(userTicket.getTicketId(),userTicket );
            }
        }
        List<TicketListJsonBean> allTicketList = emTiTicketMapperExt.allTicketList();
        List<TicketListJsonBean> ticketListJsonBeanList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(allTicketList)){
           for(TicketListJsonBean ticket : allTicketList ){
               //@TODO 此处有油bug未判断数量，仅判断是够存在
               if(userTicketMap.get(ticket.getTicketId()) == null){
                   //根据ticketId查询品类名称
                   List<String> list = emTiUserTicketMapperExt.selectCategoryNameByticketId(ticket.getTicketId());
                   StringBuffer sb = new StringBuffer();
                   if(!CollectionUtils.isEmpty(list)){
                       for(String item:list){
                           sb.append(item).append(",");
                       }
                   }
                   ticket.setCategoryNameAttrs(sb.toString());
                   ticketListJsonBeanList.add(ticket);
               }
           }
        }
        return APIResponse.success(ticketListJsonBeanList);
    }

    @Transactional
    @Override
    public APIResponse receive(String ticketId, String userId, String token) {
        SessionManagerJsonBean sessionManagerJsonBean = defaultRedisService.get(token, SessionManagerJsonBean.class);
        EmTiTicket emTiTicket = emTiTicketMapperExt.selectByPrimaryKey(ticketId);
        EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
        emTiUserTicket.setId(CommonUtil.getUUID());
        emTiUserTicket.setTicketId(ticketId);
        emTiUserTicket.setUserId(userId);
        Integer validType = emTiTicket.getValidType();
        if(validType == Constants.ValidType.FIXED_VALUE){
            emTiUserTicket.setEfstartTime(emTiTicket.getEfstartTime());
            emTiUserTicket.setEfendTime(emTiTicket.getEfendTime());
        }else{
            Date efstartTime = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(efstartTime);
            calendar.add(Calendar.DAY_OF_MONTH, emTiTicket.getValidVal());
            Date efendTime = calendar.getTime();
            emTiUserTicket.setEfstartTime(efstartTime);
            emTiUserTicket.setEfendTime(efendTime);
        }
        emTiUserTicket.setIsUse(Constants.TicketIsUse.NOT_USE);
        emTiUserTicket.setDeductionAmt(emTiTicket.getCost());
        emTiUserTicket.setCreatedBy(sessionManagerJsonBean.getUserName());
        emTiUserTicketMapperExt.insertSelective(emTiUserTicket);
        emTiTicket.setDrawCount(emTiTicket.getDrawCount() == null ? 1 : emTiTicket.getDrawCount() + 1);
        emTiTicketMapperExt.updateByPrimaryKeySelective(emTiTicket);
        return APIResponse.success("领取成功！");
    }

    @Override
    public APIResponse getTicketListOnSubmitOrder(String[] contractGoodsIds, String userId, BigDecimal totalAmount) {
        //查询该用户所有的券信息
        TicketJsonBean ticketJsonBean = new TicketJsonBean();
        ticketJsonBean.setUserId(userId);
        ticketJsonBean.setIsUse(Constants.TicketIsUse.NOT_USE);
        List<TicketJsonBean> ticketJsonBeanList = emTiUserTicketMapperExt.ticketList(ticketJsonBean);
        if(CollectionUtils.isEmpty(ticketJsonBeanList)){
            return APIResponse.success("");
        }
        //根据商品主键查询商品对应的分类主键
        List<EmCmContractGoods> emCmContractGoodsList = emCmContractGoodsMapperExt.selectListByIds(contractGoodsIds);

        List<String> categoryIds = new ArrayList<>();
        for(EmCmContractGoods emCmContractGoods : emCmContractGoodsList){
            categoryIds.add(emCmContractGoods.getCatgoryId());
        }
        //根据分类主键查询有哪些券可以用于该分类
        List<EmTiTicket> emTiTicketList = emTiTicketMapperExt.getTicketListByCategoryIds(categoryIds);
        Map<String, EmTiTicket> emTiTicketMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(emTiTicketList)){
           for(EmTiTicket emTiTicket :emTiTicketList){
               emTiTicketMap.put(emTiTicket.getTicketId(), emTiTicket);
           }
        }
        //重构本次购买商品可以使用的优惠券
        List<TicketJsonBean> resultTicketJsonBeanList = new ArrayList<>();
        for(TicketJsonBean ticket : ticketJsonBeanList){
            //判断满减
            if(ticket.getUseCondition() == Constants.UseCondition.FULL_SUBTRACTION){
                if(ticket.getUseConditionValue().compareTo(totalAmount) > 0){
                    continue;
                }
            }
            //判断时间
            if(System.currentTimeMillis() > DateUtil.getEndDateTimeOfDay(ticket.getEfendTime()).getTime()){
                continue;
            }
            if(ticket.getUseScope() == Constants.UseScope.COMMON_PUBLIC){
                //设置描述
                setCatagoryNameAttrs(ticket);
                resultTicketJsonBeanList.add(ticket);
            }else{
                if(emTiTicketMap.containsKey(ticket.getTicketId())){
                    //设置描述
                    setCatagoryNameAttrs(ticket);
                    resultTicketJsonBeanList.add(ticket);
                }
            }
        }
        return APIResponse.success(resultTicketJsonBeanList);
    }

    private void setCatagoryNameAttrs(TicketJsonBean jsonBean){
        //根据ticketId查询品类名称
        List<String> list = emTiUserTicketMapperExt.selectCategoryNameByticketId(jsonBean.getTicketId());
        StringBuffer sb = new StringBuffer();
        if(!CollectionUtils.isEmpty(list)){
            for(String item:list){
                sb.append(item).append(",");
            }
        }
        jsonBean.setCategoryNameAttrs(sb.toString());
    }

    @Override
    public APIResponse calculatePrice(String[] contractGoodsIds, Integer[] numbers, String userId, String ticketId) {
        Map<String, Integer> goodsAndNumber = new HashMap<>();
        for(int i = 0; i < contractGoodsIds.length; i++){
            goodsAndNumber.put(contractGoodsIds[i], numbers[i]);
        }
        //根据商品主键查询商品对应的分类主键
        List<EmCmContractGoods> emCmContractGoodsList = emCmContractGoodsMapperExt.selectListByIds(contractGoodsIds);
        Map<String, List<EmCmContractGoods>> categroyGoodsMap = new HashMap<>();
        //相同分类的商品进行汇总
       for(EmCmContractGoods emCmContractGoods : emCmContractGoodsList){
           if(categroyGoodsMap.containsKey(emCmContractGoods.getCatgoryId())){
               categroyGoodsMap.get(emCmContractGoods.getCatgoryId()).add(emCmContractGoods);
           }else{
               List<EmCmContractGoods> indexGoodsList = new ArrayList<>();
               indexGoodsList.add(emCmContractGoods);
               categroyGoodsMap.put(emCmContractGoods.getCatgoryId(), indexGoodsList);
           }
       }
       //计算每一个品类总金额
        Map<String, BigDecimal> amountMap = new HashMap<>();
        for(Map.Entry<String, List<EmCmContractGoods>> entry : categroyGoodsMap.entrySet()){
            String key = entry.getKey();
            BigDecimal categoryAmount = new BigDecimal(0);
            List<EmCmContractGoods> value = entry.getValue();
            for(EmCmContractGoods emCmContractGoods : value){
                BigDecimal contractPrice = emCmContractGoods.getContractPrice();
                String goodsId = emCmContractGoods.getContractGoodsId();
                Integer amount = goodsAndNumber.get(goodsId);
                BigDecimal currentGoodsAmount = contractPrice.multiply(new BigDecimal(amount));
                categoryAmount = categoryAmount.add(currentGoodsAmount);
            }
            amountMap.put(key, categoryAmount);
        }
        //所有商品总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for(Map.Entry<String, BigDecimal> entry : amountMap.entrySet()){
            String key = entry.getKey();
            totalAmount = totalAmount.add(entry.getValue());
        }
        //需要进行计算的金额
        BigDecimal inUseAmount = new BigDecimal(0);
        //根据代金券主键查询代金券
        EmTiTicket emTiTicket = emTiTicketMapperExt.selectByPrimaryKey(ticketId);
        AmountJsonBean  amountJsonBean = new AmountJsonBean();
        //使用范围是全场通用
        if(emTiTicket.getUseScope() == Constants.UseScope.COMMON_PUBLIC){
            inUseAmount = totalAmount;
            //代金券
            if(emTiTicket.getTicketType() == Constants.TicketType.CASH_COUPON){
                //使用条件是满xx元可用
                if(emTiTicket.getUseCondition() == Constants.UseCondition.FULL_SUBTRACTION){
                    //判断条件值
                    if(inUseAmount.compareTo(emTiTicket.getUseConditionValue()) > 0){
                        amountJsonBean.setTotalAmount(inUseAmount);
                        amountJsonBean.setActualAmount(inUseAmount.subtract(emTiTicket.getCost()));
                        amountJsonBean.setDiscountAmpunt(emTiTicket.getCost());
                    }else{
                        amountJsonBean.setTotalAmount(inUseAmount);
                        amountJsonBean.setActualAmount(new BigDecimal(0));
                        amountJsonBean.setDiscountAmpunt(new BigDecimal(0));
                    }
                }else{
                    //使用条件为无限制
                    amountJsonBean.setTotalAmount(inUseAmount);
                    amountJsonBean.setActualAmount(inUseAmount.subtract(emTiTicket.getCost()));
                    amountJsonBean.setDiscountAmpunt(emTiTicket.getCost());
                }
            }else{
                //折扣券
                amountJsonBean.setTotalAmount(inUseAmount);
                amountJsonBean.setActualAmount(inUseAmount.multiply(emTiTicket.getCost()));
                amountJsonBean.setDiscountAmpunt(inUseAmount.subtract(inUseAmount.multiply(emTiTicket.getCost())));
            }
        }else{
            //使用范围为按分类
            //查询该代金券对应的分类
            EmTiTicketCatetgory emTiTicketCatetgory = new EmTiTicketCatetgory();
            emTiTicketCatetgory.setTicketId(ticketId);
            List<EmTiTicketCatetgory> emTiTicketCatetgories = emTiTicketCatetgoryMapperExt.selectList(emTiTicketCatetgory);
            Map<String, EmTiTicketCatetgory> emTiTicketCatetgoryMap = new HashMap<>();
            for(EmTiTicketCatetgory indexCategroy: emTiTicketCatetgories){
                emTiTicketCatetgoryMap.put(indexCategroy.getCategoryId(), indexCategroy);
            }

            for( Map.Entry<String, BigDecimal> entry : amountMap.entrySet()){
                if(emTiTicketCatetgoryMap.containsKey(entry.getKey())){
                    inUseAmount = inUseAmount.add(amountMap.get(entry.getKey()));
                }
            }

            //差价 总金额-分类总价
            BigDecimal diffAmount = totalAmount.subtract(inUseAmount);

            //代金券
            if(emTiTicket.getTicketType() == Constants.TicketType.CASH_COUPON){
                //使用条件是满xx元可用
                if(emTiTicket.getUseCondition() == Constants.UseCondition.FULL_SUBTRACTION){
                    //判断条件值
                    if(inUseAmount.compareTo(emTiTicket.getUseConditionValue()) > 0){
                        amountJsonBean.setTotalAmount(inUseAmount.add(diffAmount));
                        amountJsonBean.setActualAmount(inUseAmount.subtract(emTiTicket.getCost()).add(diffAmount));
                        amountJsonBean.setDiscountAmpunt(emTiTicket.getCost());
                    }else{
                        amountJsonBean.setTotalAmount(inUseAmount.add(diffAmount));
                        amountJsonBean.setActualAmount(new BigDecimal(0));
                        amountJsonBean.setDiscountAmpunt(new BigDecimal(0));
                    }
                }else{
                    //使用条件为无限制
                    amountJsonBean.setTotalAmount(inUseAmount.add(diffAmount));
                    amountJsonBean.setActualAmount(inUseAmount.subtract(emTiTicket.getCost()).add(diffAmount));
                    amountJsonBean.setDiscountAmpunt(emTiTicket.getCost());
                }
            }else{
                //折扣券
                amountJsonBean.setTotalAmount(inUseAmount.add(diffAmount));
                amountJsonBean.setActualAmount(inUseAmount.multiply(emTiTicket.getCost()).add(diffAmount));
                amountJsonBean.setDiscountAmpunt(inUseAmount.subtract(inUseAmount.multiply(emTiTicket.getCost())));
            }
        }
        if(amountJsonBean.getActualAmount().compareTo(new BigDecimal(0.01)) < 0){
            amountJsonBean.setActualAmount(new BigDecimal(0.01));
        }
        amountJsonBean.setDiscountAmpunt(amountJsonBean.getDiscountAmpunt().setScale(2, BigDecimal.ROUND_HALF_UP));
        amountJsonBean.setTotalAmount(amountJsonBean.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        amountJsonBean.setActualAmount(amountJsonBean.getActualAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        return APIResponse.success(amountJsonBean);
    }

    @Override
    public Integer showTickets() {
        /**
         * @TODO 验证是否显示大礼包 使用魔法值即可 1 不显示  2 显示
         * */
        EmTiTicket emTiTicket = new EmTiTicket();
        emTiTicket.setIssueWay(Constants.IssueWay.MANUAL_RECEIPT);
        List<EmTiTicket> emTiTicketList = emTiTicketMapperExt.selectList(emTiTicket);
        if(CollectionUtils.isEmpty(emTiTicketList)){
            return 1;
        }else{
            //验证券数量是否全部已经领取完，只要有一类券满足条件就显示
            for(EmTiTicket ticket : emTiTicketList){
                if(ticket.getIssueTotal() > ticket.getDrawCount()){
                    return 2;
                }
            }
        }
        return 1;
    }
}
