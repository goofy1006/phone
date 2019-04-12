package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.shopping.model.EmOdEvaluation;
import com.shopping.model.jsonbean.EmOdOrdersJsonBean;
import com.shopping.model.jsonbean.PayModelBackJsonBean;
import com.shopping.model.jsonbean.PayModelJsonBean;
import com.shopping.service.EmOdOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangas on 2018/7/31.
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/order/")
public class EmOdOrdersController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(EmOdOrdersController.class);

    @Autowired
    private EmOdOrdersService emOdOrdersService;

    @ApiOperation(value = "我的订单", notes = "全部订单：不需要传入参数|待付款：10110005|待发货：10110010|待收货：10110015|已完成：10110040  订单评价：isAudited 0:待评价，1：已评价")
    @RequestMapping(value = "/currentOrders.do")
    public APIResponse<List<EmOdOrdersJsonBean>> currentOrders(
            @ApiParam(value = "用户ID",name = "customerId")
            @RequestParam String customerId,
            @ApiParam(value = "每页显示条数",name = "pageSize")
            @RequestParam Integer pageSize,
            @ApiParam(value = "页码",name = "pageNum")
            @RequestParam Integer pageNum,
            @ApiParam(value = "订单状态", name = "orderStatus")
            @RequestParam(required = false) Integer orderStatus
    ){
        return emOdOrdersService.selectOrdersByStatus(customerId,orderStatus,pageSize, pageNum);
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @ApiOperation(value = "删除订单")
    @RequestMapping(value = "delOrders.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse delOrders(
            @ApiParam(value = "订单ID",name = "orderId")
            @RequestParam String orderId
    ){
            return emOdOrdersService.delOrders(orderId);
    }

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    @ApiOperation(value = "确认收货")
    @RequestMapping(value = "suOrders.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse suOrders(
            @ApiParam(value = "订单ID",name = "orderId")
            @RequestParam String orderId
    ){
            return emOdOrdersService.suOrders(orderId);
         }



    @ApiOperation(value = "根据订单编号查询订单明细")
    @RequestMapping(value = "orderDescribe.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse orderDescribe(
            @ApiParam(value = "订单编号",name = "orderNo")
            @RequestParam String orderNo
    ) {
        return emOdOrdersService.orderDescribe(orderNo);

    }
}
