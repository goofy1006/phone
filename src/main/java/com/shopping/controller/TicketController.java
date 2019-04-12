package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.TicketJsonBean;
import com.shopping.model.jsonbean.TicketListJsonBean;
import com.shopping.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangas on 2018/8/11.
 */
@Api(tags = "代金券管理")
@RestController
@RequestMapping(value = "/ticket/")
public class TicketController extends BaseController {


    @Autowired
    private TicketService ticketService;

    @ApiOperation(value = "获取当前用户代金券列表", notes = "")
    @RequestMapping(value = "ticketList.do", method = RequestMethod.POST)
    public APIResponse<List<TicketJsonBean>> ticketList(
            @ApiParam(name = "customerId", value = "用户ID", required = true)
            @RequestParam String customerId
    ){
        TicketJsonBean ticketJsonBean = new TicketJsonBean();
        ticketJsonBean.setUserId(customerId);
        return ticketService.ticketList(ticketJsonBean);
    }

    @ApiOperation(value = "获取代金券列表剔除已经领取的", notes = "")
    @RequestMapping(value = "allTicketList.do", method = RequestMethod.POST)
    public APIResponse allTicketList(
            @ApiParam(name = "customerId", value = "用户ID")
            @RequestParam(required = false, defaultValue = "new user") String customerId
    ){
        TicketListJsonBean ticketListJsonBean = new TicketListJsonBean();
        ticketListJsonBean.setUserId(customerId);
        return ticketService.allTicketList(ticketListJsonBean);
    }

    @ApiOperation(value = "代金券领取", notes = "")
    @RequestMapping(value = "receive.do", method = RequestMethod.POST)
    public APIResponse receive(
            HttpServletRequest request,
            @ApiParam(name = "ticketId", value = "券主键", required = true)
            @RequestParam String ticketId,
            @ApiParam(name = "customerId", value = "客户主键", required = true)
            @RequestParam String customerId
    ){
        return ticketService.receive(ticketId, customerId,request.getParameter("token"));
    }

    @ApiOperation(value = "提交订单时获取优惠券列表", notes = "")
    @RequestMapping(value = "getTicketListOnSubmitOrder.do", method = RequestMethod.POST)
    public APIResponse getTicketListOnSubmitOrder(
            @ApiParam(name = "contractGoodsIds", value = "商品主键数组", required = true)
            @RequestParam String[] contractGoodsIds,
            @ApiParam(name = "customerId", value = "客户主键", required = true)
            @RequestParam String customerId,
            @ApiParam(name = "totalAmount", value = "订单总额", required = true)
            @RequestParam BigDecimal totalAmount
    ){
        return ticketService.getTicketListOnSubmitOrder(contractGoodsIds, customerId, totalAmount);
    }

    @ApiOperation(value = "选择代金券计算价格", notes = "")
    @RequestMapping(value = "calculatePrice.do", method = RequestMethod.POST)
    public APIResponse calculatePrice(
            @ApiParam(name = "contractGoodsIds", value = "商品主键数组", required = true)
            @RequestParam String[] contractGoodsIds,
            @ApiParam(name = "numbers", value = "商品对应数量", required = true)
            @RequestParam Integer[] numbers,
            @ApiParam(name = "customerId", value = "客户主键", required = true)
            @RequestParam String customerId,
            @ApiParam(name = "ticketId", value = "代金券主键", required = true)
            @RequestParam String ticketId
    ){
        return ticketService.calculatePrice(contractGoodsIds, numbers, customerId, ticketId);
    }







}
