package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmOdOrderDetail extends BaseDomain {
    private String orderDetailId;

    private String orderId;

    private String goodsId;

    private String goodsName;

    private String storeId;

    private BigDecimal count;

    private BigDecimal autCount;

    private BigDecimal pulledQty;

    private Integer unit;

    private String remark;

    private String currentId;

    private BigDecimal salesPrice;

    private BigDecimal amount;

    private BigDecimal autAmt;

    private BigDecimal pulledAmt;

    private Integer auditRemark;

    private Short isEvaluated;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long version;

    private Integer aftersalesState;

    private BigDecimal priceB;
    private BigDecimal priceA;//成本价
    private BigDecimal priceC;//出厂价

    public BigDecimal getPriceA() {
        return priceA;
    }

    public void setPriceA(BigDecimal priceA) {
        this.priceA = priceA;
    }

    public BigDecimal getPriceC() {
        return priceC;
    }

    public void setPriceC(BigDecimal priceC) {
        this.priceC = priceC;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getAutCount() {
        return autCount;
    }

    public void setAutCount(BigDecimal autCount) {
        this.autCount = autCount;
    }

    public BigDecimal getPulledQty() {
        return pulledQty;
    }

    public void setPulledQty(BigDecimal pulledQty) {
        this.pulledQty = pulledQty;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId == null ? null : currentId.trim();
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAutAmt() {
        return autAmt;
    }

    public void setAutAmt(BigDecimal autAmt) {
        this.autAmt = autAmt;
    }

    public BigDecimal getPulledAmt() {
        return pulledAmt;
    }

    public void setPulledAmt(BigDecimal pulledAmt) {
        this.pulledAmt = pulledAmt;
    }

    public Integer getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(Integer auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Short getIsEvaluated() {
        return isEvaluated;
    }

    public void setIsEvaluated(Short isEvaluated) {
        this.isEvaluated = isEvaluated;
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

    public Integer getAftersalesState() {
        return aftersalesState;
    }

    public void setAftersalesState(Integer aftersalesState) {
        this.aftersalesState = aftersalesState;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }
}