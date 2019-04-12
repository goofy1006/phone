package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.util.Date;

public class EmSysDictionary extends BaseDomain {
    private Integer dictKey;

    private Short dictNo;

    private String dictCode;

    private String dictValue;

    private String dictName;

    private String bizCategory;

    private Short isInactive;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long version;

    private Long dictOrder;

    public Integer getDictKey() {
        return dictKey;
    }

    public void setDictKey(Integer dictKey) {
        this.dictKey = dictKey;
    }

    public Short getDictNo() {
        return dictNo;
    }

    public void setDictNo(Short dictNo) {
        this.dictNo = dictNo;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory == null ? null : bizCategory.trim();
    }

    public Short getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Short isInactive) {
        this.isInactive = isInactive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(Long dictOrder) {
        this.dictOrder = dictOrder;
    }
}