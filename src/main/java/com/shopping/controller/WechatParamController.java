package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.fastjson.JSONObject;
import com.shopping.common.HttpUtil;
import com.shopping.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangas on 2018/8/13.
 */
@Api(tags = "获取微信分享参数",value = "")
@RestController
@RequestMapping(value = "/wechat/")
public class WechatParamController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WechatParamController.class);

    @Value("${wechat.appid:}")
    private String appId;

    @Value("${wechat.appsecret:}")
    private String appSecret;

    private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private String apiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    private String accessToken;

    private String jsApiTicket;

    private Long getTiketTime = 0L;

    private Long getTokenTime = 0L;

    private Long tokenExpireTime = 0L;

    private Long ticketExpireTime = 0L;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WechatService wechatService;

    @ApiOperation(value = "获取参数", notes = "")
    @RequestMapping(value = "param.do", method = RequestMethod.POST)
    public Map<String, String> param(
            @ApiParam(name = "url", value = "URL", required = true)
            @RequestParam String url){
        logger.error("param : url==>" + url);
        long now = System.currentTimeMillis();
        //当前时间
        logger.error("currentTime====>" + now + "ms");
        if(StringUtils.isBlank(accessToken) || (now - getTiketTime > tokenExpireTime * 1000)){
            JSONObject tokenInfo = getAccessToken();
            if(tokenInfo != null){
                logger.error("tokenInfo====>" + tokenInfo.toJSONString());
                accessToken = tokenInfo.getString("access_token");
                tokenExpireTime = tokenInfo.getLongValue("expires_in");
                //获取token的时间
                getTokenTime = System.currentTimeMillis();
                logger.error("accessToken====>" + accessToken);
                logger.error("tokenExpireTime====>" + tokenExpireTime + "s");
                logger.error("getTokenTime====>" + getTokenTime + "ms");
            }else{
                logger.error("====>tokenInfo is null~");
                logger.error("====>failure of getting tokenInfo,please do some check~");
            }
        }
        //判断jsApiTicket是否已经存在或者是否过期
        if(StringUtils.isBlank(jsApiTicket)||(now - getTiketTime > ticketExpireTime*1000)){
            JSONObject ticketInfo = getJsApiTicket();
            if(ticketInfo!=null){
                logger.error("ticketInfo====>" + ticketInfo.toJSONString());
                jsApiTicket = ticketInfo.getString("ticket");
                ticketExpireTime = ticketInfo.getLongValue("expires_in");
                getTiketTime = System.currentTimeMillis();
                logger.error("jsApiTicket====>" + jsApiTicket);
                logger.error("ticketExpireTime====>" + ticketExpireTime + "s");
                logger.error("getTiketTime====>" + getTiketTime + "ms");
            }else{
                logger.error("====>ticketInfo is null~");
                logger.error("====>failure of getting tokenInfo,please do some check~");
            }
        }
        //生成微信权限验证的参数
        Map<String, String> wechatParam= makeWXTicket(jsApiTicket,url);
        return wechatParam;
    }

    private JSONObject getAccessToken(){
        String requestUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET",appSecret);
        logger.info("getAccessToken.requestUrl====>"+requestUrl);
        JSONObject result = HttpUtil.doGet(requestUrl);
        return result ;
    }

    private JSONObject getJsApiTicket(){
        String requestUrl = apiTicketUrl.replace("ACCESS_TOKEN", accessToken);
        logger.error("getJsApiTicket.requestUrl====>" + requestUrl);
        JSONObject result = HttpUtil.doGet(requestUrl);
        return result;
    }

    //生成微信权限验证的参数
    public Map<String, String> makeWXTicket(String jsApiTicket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        logger.info("String1=====>"+string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            logger.info("signature=====>"+signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error("WeChatController.makeWXTicket=====Start");
            logger.error(e.getMessage(),e);
            logger.error("WeChatController.makeWXTicket=====End");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("WeChatController.makeWXTicket=====Start");
            logger.error(e.getMessage(),e);
            logger.error("WeChatController.makeWXTicket=====End");
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsApiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appid", appId);

        return ret;
    }

    //字节数组转换为十六进制字符串
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    //生成随机字符串
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }
    //生成时间戳
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    @ApiOperation(value = "被分享人打开页面绑定", notes = "")
    @RequestMapping(value = "bind.do", method = RequestMethod.POST)
    public APIResponse bind(
            @ApiParam(name = "sourceOpenId", value = "分享人OpenId", required = true)
            @RequestParam String sourceOpenId,
            @ApiParam(name = "currentOpenId", value = "被分享人OpenId", required = true)
            @RequestParam String currentOpenId
    ){
        return wechatService.bind(sourceOpenId, currentOpenId);
    }

}
