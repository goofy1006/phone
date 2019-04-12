package com.shopping.common;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by think on 2018/9/14.
 */
public class ERPUtils {

    private static Logger logger = LoggerFactory.getLogger(ERPUtils.class);
    public static  void pushOrdersToERP(String url){
//        Properties messageInfo = PropertiesInfoUtil.getMessageInfo();
//        String url=(String)messageInfo.get("pushOrdersToERP_url");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            CloseableHttpResponse execute = httpClient.execute(post);
            logger.info("ERPUtils订单同步结果:"+execute);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("ERPUtils订单同步ERP失败",e);
        }
    }
}
