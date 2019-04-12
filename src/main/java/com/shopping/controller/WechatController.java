package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.shopping.common.AdvancedUtil;
import com.shopping.model.jsonbean.SessionManagerJsonBean;
import com.shopping.model.jsonbean.WeixinOauth2Token;
import com.shopping.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangas on 2018/7/30.
 */
@Controller
@RequestMapping(value = "/wechat/")
public class WechatController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Value("${wechat.appid:}")
    private String appId;

    @Value("${wechat.appsecret:}")
    private String appSecret;

    @Value("${wechat.url:}")
    private String url;

    @Autowired
    private WechatService wechatService;

    @RequestMapping(value = "homepage.do", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request){
        String code = request.getParameter("code");
        WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
        String openId = weixinOauth2Token.getOpenId();
      /*  String code = "061X81Bx0o6Tmi1taaAx0w8cBx0X81BR";
        String openId = "ouyU7XwGAiY5n6Y9rcXXhEGHszvXU";*/
        if(StringUtils.isEmpty(openId) && !StringUtils.isEmpty(code)){
            logger.error("get user openid fail!");
        }else{
            SessionManagerJsonBean sessionManagerJsonBean = wechatService.validataAutoLogin(openId);
            return "redirect:"+url+"?token="+sessionManagerJsonBean.getToken()+"&customerId="+sessionManagerJsonBean.getCustomerId()+"&userName="+sessionManagerJsonBean.getUserName()+"&tel="+sessionManagerJsonBean.getTel()+"&openId="+sessionManagerJsonBean.getOpenId();
        }
        return "redirect:"+url;
    }


}
