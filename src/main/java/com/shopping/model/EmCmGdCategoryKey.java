package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;

public class EmCmGdCategoryKey extends BaseDomain {
    private String catgoryId;

    private String contractCustomerId;

    public String getCatgoryId() {
        return catgoryId;
    }

    public void setCatgoryId(String catgoryId) {
        this.catgoryId = catgoryId == null ? null : catgoryId.trim();
    }

    public String getContractCustomerId() {
        return contractCustomerId;
    }

    public void setContractCustomerId(String contractCustomerId) {
        this.contractCustomerId = contractCustomerId == null ? null : contractCustomerId.trim();
    }
}