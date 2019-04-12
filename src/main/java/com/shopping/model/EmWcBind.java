package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;

public class EmWcBind extends BaseDomain {
    private String uuid;

    private String sourceOpenid;

    private String currentOpenid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getSourceOpenid() {
        return sourceOpenid;
    }

    public void setSourceOpenid(String sourceOpenid) {
        this.sourceOpenid = sourceOpenid == null ? null : sourceOpenid.trim();
    }

    public String getCurrentOpenid() {
        return currentOpenid;
    }

    public void setCurrentOpenid(String currentOpenid) {
        this.currentOpenid = currentOpenid == null ? null : currentOpenid.trim();
    }
}