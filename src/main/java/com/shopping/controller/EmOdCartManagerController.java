package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.EmOdCartJsonBean;
import com.shopping.service.IEmOdCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lenovo on 2018/7/30.
 */
@Api(tags="购物车管理")
@Controller
@RequestMapping(value = "/cart/")
public class EmOdCartManagerController {

    @Autowired
    IEmOdCartService iEmOdCartService;

    /**
     * 购物车查询
     * @param customerId
     * @return
     */
    @ApiOperation(value = "购物车查询")
    @RequestMapping(value = "selectListCart.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmOdCartJsonBean>> selectListCart(
            @ApiParam(name = "customerId",value = "客户ID",required = true)
            @RequestParam String customerId,
            @ApiParam(name = "goodsId",value = "商品ID")
            @RequestParam String goodsId
    ){
        return iEmOdCartService.selectListCart(customerId,goodsId);
    }

    /**
     * 插入购物车
     * @param contractGoodsId
     * @return
     */
    @ApiOperation(value = "购物车添加")
    @RequestMapping(value = "insertCart.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse insertCart(
            @ApiParam(value = "商品ID",name = "contractGoodsId")
           @RequestParam String contractGoodsId,
            @ApiParam(value = "客户ID",name = "customerId")
            @RequestParam String customerId
    ){
            return iEmOdCartService.insertCart(contractGoodsId,customerId);
    }

    /**
     * 根据购物车ID删除商品
     * @param cartId
     * @return
     */
    @ApiOperation(value = "根据购物车ID删除商品")
    @RequestMapping(value = "deleGoodsCart.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse deleGoodsCart(
            @ApiParam(name = "cartId",value = "购物车ID")
            @RequestParam String cartId){
            return iEmOdCartService.deleGoodsCart(cartId);
    }

    /**
     * 购物车商品数量修改
     * @param cartId
     * @return
     */
    @ApiOperation(value = "购物车商品数量修改")
    @RequestMapping(value = "editCartGoodsNum.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse editCartGoodsNum(
            @ApiParam(name = "cartId",value = "购物车ID")
            @RequestParam String cartId,
            @ApiParam(value = "数量",name = "num")
            @RequestParam Integer num){
            return iEmOdCartService.editCartGoodsNum(cartId,num);
    }

    /**
     * 购物车生成订单
     * @param ordersArrs
     */
    @ApiOperation(value = "购物车生成订单")
    @RequestMapping(value = "insertOrders.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse insertOrders(
            @ApiParam(value = "订单ID串",name = "ordersArrs",required = true)
            @RequestParam String[] ordersArrs,
            @ApiParam(name = "addressId",value = "地址ID",required = true)
            @RequestParam String addressId,
            @ApiParam(value = "客户ID",name = "customerId",required = true)
            @RequestParam String customerId,
            @ApiParam(value = "优惠券ID",name = "ticketId")
            @RequestParam String ticketId,
            @ApiParam(value = "总金额",name = "totalAmount")
            @RequestParam BigDecimal totalAmount,
            @ApiParam(value = "支付金额",name = "actualAmount")
            @RequestParam BigDecimal actualAmount,
            @ApiParam(value = "优惠金额",name = "discountAmpunt")
            @RequestParam BigDecimal discountAmpunt,
            @ApiParam(value = "数量集合",name = "numbers")
            @RequestParam Integer[] numbers,
            @ApiParam(value = "用户券ID",name = "userTicketId")
            @RequestParam String userTicketId

    ){
        return iEmOdCartService.insertOrders(ordersArrs,addressId,customerId,ticketId,totalAmount,actualAmount,discountAmpunt,numbers,userTicketId);
    }
}
