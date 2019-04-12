package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.util.Date;

public class PhoneEmPpAdvertisement extends BaseDomain {
    private String advertisementId;

    private Integer advertisementType;

    private Integer advertisementOrder;

    private String advertisementUrl;

    private Short isInactive;

    private Date createdTime;

    private String createdBy;

    private Date updatedTime;

    private String updateBy;

    private Long version;

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId == null ? null : advertisementId.trim();
    }

    public Integer getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(Integer advertisementType) {
        this.advertisementType = advertisementType;
    }

    public Integer getAdvertisementOrder() {
        return advertisementOrder;
    }

    public void setAdvertisementOrder(Integer advertisementOrder) {
        this.advertisementOrder = advertisementOrder;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl == null ? null : advertisementUrl.trim();
    }

    public Short getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Short isInactive) {
        this.isInactive = isInactive;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}