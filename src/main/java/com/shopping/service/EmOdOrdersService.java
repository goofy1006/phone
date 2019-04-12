package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmOdEvaluation;
import com.shopping.model.jsonbean.EmOdOrdersJsonBean;
import com.shopping.model.jsonbean.PayBackResultJsonBean;
import com.shopping.model.jsonbean.PayModelBackJsonBean;
import com.shopping.model.jsonbean.PayModelJsonBean;

import java.util.List;

/**
 * Created by zhangas on 2018/7/31.
 */
public interface EmOdOrdersService {

    /**
     * 订单 查询
     * @param customerId
     * @param orderStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    APIResponse<List<EmOdOrdersJsonBean>> selectOrdersByStatus(String customerId, Integer orderStatus, Integer pageSize,Integer pageNum);

    /**
     * 订单删除
     * @param orderId
     * @return
     */
    public APIResponse delOrders(String orderId);

    /**
     * 订单确认
     * @param orderId
     * @return
     */
    public APIResponse suOrders(String orderId);

    /**
     * 订单支付
     * @param orderId
     * @return
     */
    public APIResponse<PayModelJsonBean> payOrder(String orderId);

    /**
     * 支付结果异步通知
     * @param payReuslt
     */
    public PayBackResultJsonBean payOrderBack(String payReuslt);


    public APIResponse orderDescribe(String orderNo);
}
