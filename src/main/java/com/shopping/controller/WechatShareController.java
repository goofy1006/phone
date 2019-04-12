package com.shopping.controller;

import com.shopping.common.AdvancedUtil;
import com.shopping.model.jsonbean.WeixinOauth2Token;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangas on 2018/8/14.
 */
@Controller
@RequestMapping(value = "/wechat/")
public class WechatShareController {

    @Value("${wechat.appid:}")
    private String appId;

    @Value("${wechat.appsecret:}")
    private String appSecret;

    @Value("${backup.url:}")
    private String backupUrl;

    @Value("${wechat.url:}")
    private String wechatUrl;


    @RequestMapping(value = "sharelink.do", method = RequestMethod.GET)
    public String shareLink(HttpServletRequest request, String sourceOpenId, String goodsId){
        request.getSession().setAttribute("sourceOpenId", sourceOpenId);
        request.getSession().setAttribute("goodsId", goodsId);
        String backUrl = backupUrl.concat("wechat/redirect.do");
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId
                + "&redirect_uri="+ URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=123#wechat_redirect";
        return "redirect:"+url;
    }

    @RequestMapping(value = "redirect.do", method = RequestMethod.GET)
    public String redirect(HttpServletRequest request){
        Object indexSourceOpenId = request.getSession().getAttribute("sourceOpenId");;
        Object indexGoodsId = request.getSession().getAttribute("goodsId");
        String sourceOpenId = null;
        if(indexSourceOpenId != null){
            sourceOpenId = (String)indexSourceOpenId;
        }
        String goodsId = null;
        if(indexGoodsId != null){
            goodsId = (String)indexGoodsId;
        }
        String code = request.getParameter("code");
        WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
        String openId = weixinOauth2Token.getOpenId();
        if(StringUtils.isEmpty(goodsId)){
            return "redirect:"+wechatUrl+"?currentOpenId="+openId+"&sourceOpenId="+sourceOpenId;
        }
        return "redirect:"+wechatUrl+"detail/goods_details?goodsId="+goodsId+"&currentOpenId="+openId+"&sourceOpenId="+sourceOpenId;
    }


}
