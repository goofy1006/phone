package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.util.Date;

public class EmBbRegion extends BaseDomain {
    private String regionId;

    private String parentRegionId;

    private String erpRegionId;

    private Integer regionType;

    private String regionName;

    private String regionLogo;

    private Long regionOrder;

    private Short isInactive;

    private Date createdTime;

    private String createdBy;

    private Date updatedTime;

    private String updatedBy;

    private Long version;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(String parentRegionId) {
        this.parentRegionId = parentRegionId == null ? null : parentRegionId.trim();
    }

    public String getErpRegionId() {
        return erpRegionId;
    }

    public void setErpRegionId(String erpRegionId) {
        this.erpRegionId = erpRegionId == null ? null : erpRegionId.trim();
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getRegionLogo() {
        return regionLogo;
    }

    public void setRegionLogo(String regionLogo) {
        this.regionLogo = regionLogo == null ? null : regionLogo.trim();
    }

    public Long getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(Long regionOrder) {
        this.regionOrder = regionOrder;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}