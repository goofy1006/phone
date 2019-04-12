package com.shopping.model.jsonbean;

import com.shopping.model.PhoneUser;

/**
 * Created by zhangas on 2018/7/27.
 */
public class SessionManagerJsonBean{

    private String token;

    private String customerId;

    private String userName;

    private String password;

    private String tel;

    private String openId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "{token:" + token + ", openid:" + openId + "}";
    }
}
