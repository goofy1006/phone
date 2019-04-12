package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.EmOdCartJsonBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lenovo on 2018/7/30.
 */
public interface IEmOdCartService {

    /**
     * 购物车查询
     * @param customerId
     * @return
     */
    public APIResponse<List<EmOdCartJsonBean>> selectListCart(String customerId,String goodsId);

    /**
     *插入购物车
     * @param contractGoodsId
     * @return
     */
    public APIResponse insertCart(String contractGoodsId,String customerId);

    /**
     *根据购物车ID删除商品
     * @param cartId
     * @return
     */
    public APIResponse deleGoodsCart(String cartId);

    /**
     *购物车商品数量修改
     * @param cartId
     * @param num
     * @return
     */
    public APIResponse editCartGoodsNum(String cartId,Integer num);

    /**
     * 购物车生成订单
     * @param ordersArrs
     * @return
     */
    public APIResponse insertOrders(String[] ordersArrs,String addressId,String customerId,String ticketId,BigDecimal totalAmount,BigDecimal actualAmount,BigDecimal discountAmpunt,Integer[] numbers,String userTicketId);
}
