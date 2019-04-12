package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.common.ERPUtils;
import com.shopping.model.jsonbean.PayBackResultJsonBean;
import com.shopping.model.jsonbean.PayModelBackJsonBean;
import com.shopping.model.jsonbean.PayModelJsonBean;
import com.shopping.service.EmOdOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2018/8/6.
 */
@Api(tags="订单支付")
@Controller
@RequestMapping(value = "/payback/")
public class WechatPayController {

    private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);

    @Value("${wechat.url}")
    private String whchatUrl;
    @Value("${pushOrdersToERP_url}")
    private String pushOrdersToERP_url;

    @Autowired
    private EmOdOrdersService emOdOrdersService;

    /**
     * 订单支付
     * @param orderId
     * @return
     */
    @ApiOperation(value = "订单支付")
    @RequestMapping(value = "payOrder.do",method = RequestMethod.GET)
    public @ResponseBody
    APIResponse<PayModelJsonBean> payOrder(
            @ApiParam(value = "订单ID",name = "orderId")
            @RequestParam String orderId
    ){
        return emOdOrdersService.payOrder(orderId);
    }


    /**
     * 支付结果异步通知
     * @param payReuslt
     * @return
     */
    @ApiOperation(value = "订单支付回调")
    @RequestMapping(value = "payOrderBack.do",method = RequestMethod.POST)
    public @ResponseBody
    PayBackResultJsonBean payOrderBack(
            @RequestParam String payReuslt
    ){
        logger.info("==================" + payReuslt + "==========");
        PayBackResultJsonBean payBackResultJsonBean = emOdOrdersService.payOrderBack(payReuslt);

        if(payBackResultJsonBean.getReturn_code().equals("SUCCESS")){
            //订单同步ERP
            try {
                logger.info("==================" + pushOrdersToERP_url + "==========");
                ERPUtils.pushOrdersToERP(pushOrdersToERP_url);
            } finally {
                return payBackResultJsonBean;
            }
        }

        return payBackResultJsonBean;

    }

    @ApiOperation(value = "支付同步地址跳转")
    @RequestMapping(value = "payReturnUrl_{customerId}.do")
    public String payReturnUrl(
            @PathVariable String customerId
    ){
        logger.info("====returnurl======" + customerId + "==================");
            return "redirect:"+whchatUrl+"me/my_order?customerId="+customerId;
       // return "redirect:http://phone.cnfuzheng.com/#/me/my_order?customerId=49704b7126e04b2bae70345615dc41ca";
    }
}
