package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangas on 2018/7/23.
 */
public interface PhoneUserService {

    APIResponse validataUserName(String userName);

    APIResponse validataTel(String tel);

    APIResponse smsCode(String tel);

    APIResponse register(String userName, String password, String tel, String code, String openId);

    APIResponse smsLogin(HttpServletRequest request, String tel, String code, String openId);

    APIResponse login(HttpServletRequest request,String userName, String password, String openId);

}
