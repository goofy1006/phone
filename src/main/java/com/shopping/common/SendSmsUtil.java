package com.shopping.common;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//短信发送
@Component
public  class SendSmsUtil  {

    private static Logger logger = LoggerFactory.getLogger(SendSmsUtil.class);

    public static   APIResponse sendSms(String phoneNumber, String templateCode, Map<String,String> templateParams,
                                String sysId, String userName, String signName, Integer isUp) {
            String sendUrl = System.getProperty("sendSms.semdSmsUrl");
            //发送post请求
            String result = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(sendUrl);
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("phoneNumber", phoneNumber));
            pairs.add(new BasicNameValuePair("templateCode", templateCode));
            pairs.add(new BasicNameValuePair("sysId", sysId));
            pairs.add(new BasicNameValuePair("userName", userName));
            pairs.add(new BasicNameValuePair("signName", signName));
            pairs.add(new BasicNameValuePair("isUp", String.valueOf(isUp)));
            pairs.add(new BasicNameValuePair("templateParam",  JSONObject.toJSONString(templateParams)));
            CloseableHttpResponse response = null;
            APIResponse apiResponse =null;
            try {
                post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
                response = httpClient.execute(post);
                if(response != null && response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    ObjectMapper mapper = new ObjectMapper();
                    apiResponse = mapper.readValue(entity.getContent(), APIResponse.class);
                }
                return apiResponse;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                logger.error("SendSmsUtil工具发送调用短信服务出错(UnsupportedEncodingException)",e);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                logger.error("SendSmsUtil工具发送调用短信服务出错(ClientProtocolException)",e);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("SendSmsUtil工具发送调用短信服务出错(IOException)",e);
            }finally {
                try {
                    httpClient.close();
                    if(response != null)
                    {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
    }
}
