package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;

public class PhoneCompanyMessage extends BaseDomain {
    private Integer uuid;

    private String phoneNumber;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }
}