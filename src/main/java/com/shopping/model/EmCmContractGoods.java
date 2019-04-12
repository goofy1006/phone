package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmCmContractGoods extends BaseDomain {
    private String contractGoodsId;

    private String goodsId;

    private String catgoryId;

    private String contractGoodsCode;

    private String contractGoodsName;

    private BigDecimal contractPrice;

    private BigDecimal specialPrice;

    private BigDecimal minPackage;

    private BigDecimal salesAccount;

    private BigDecimal contractScore;

    private Short contracted;

    private Short upAct;

    private Short isInactive;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long version;

    private String attrNameValue;

    public String getContractGoodsId() {
        return contractGoodsId;
    }

    public void setContractGoodsId(String contractGoodsId) {
        this.contractGoodsId = contractGoodsId == null ? null : contractGoodsId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getCatgoryId() {
        return catgoryId;
    }

    public void setCatgoryId(String catgoryId) {
        this.catgoryId = catgoryId == null ? null : catgoryId.trim();
    }

    public String getContractGoodsCode() {
        return contractGoodsCode;
    }

    public void setContractGoodsCode(String contractGoodsCode) {
        this.contractGoodsCode = contractGoodsCode == null ? null : contractGoodsCode.trim();
    }

    public String getContractGoodsName() {
        return contractGoodsName;
    }

    public void setContractGoodsName(String contractGoodsName) {
        this.contractGoodsName = contractGoodsName == null ? null : contractGoodsName.trim();
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public BigDecimal getMinPackage() {
        return minPackage;
    }

    public void setMinPackage(BigDecimal minPackage) {
        this.minPackage = minPackage;
    }

    public BigDecimal getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(BigDecimal salesAccount) {
        this.salesAccount = salesAccount;
    }

    public BigDecimal getContractScore() {
        return contractScore;
    }

    public void setContractScore(BigDecimal contractScore) {
        this.contractScore = contractScore;
    }

    public Short getContracted() {
        return contracted;
    }

    public void setContracted(Short contracted) {
        this.contracted = contracted;
    }

    public Short getUpAct() {
        return upAct;
    }

    public void setUpAct(Short upAct) {
        this.upAct = upAct;
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

    public String getAttrNameValue() {
        return attrNameValue;
    }

    public void setAttrNameValue(String attrNameValue) {
        this.attrNameValue = attrNameValue == null ? null : attrNameValue.trim();
    }
}