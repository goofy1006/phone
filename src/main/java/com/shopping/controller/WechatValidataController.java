package com.shopping.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangas on 2018/7/31.
 */
@RestController
public class WechatValidataController {

   @Value("${wechat.validata:}")
   private String wechatValidata;

    @RequestMapping(value = "{fileName}.txt", method = RequestMethod.GET)
    public String test(){
        return wechatValidata;
    }

}
