package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmOdEvaluation extends BaseDomain {
    private String evaluationId;

    private String customerId;

    private String orderDetailId;

    private String goodsId;

    private String userCode;

    private BigDecimal goodsEvaluation;

    private BigDecimal carrierEvaluation;

    private BigDecimal serviceEvaluation;

    private BigDecimal evaluationScore;

    private String evaluationContent;

    private Date evaluationTime;

    private Short isInactive;

    private Date createdTime;

    private String createdBy;

    private Date updatedTime;

    private String updatedBy;

    private Long version;

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId == null ? null : evaluationId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public BigDecimal getGoodsEvaluation() {
        return goodsEvaluation;
    }

    public void setGoodsEvaluation(BigDecimal goodsEvaluation) {
        this.goodsEvaluation = goodsEvaluation;
    }

    public BigDecimal getCarrierEvaluation() {
        return carrierEvaluation;
    }

    public void setCarrierEvaluation(BigDecimal carrierEvaluation) {
        this.carrierEvaluation = carrierEvaluation;
    }

    public BigDecimal getServiceEvaluation() {
        return serviceEvaluation;
    }

    public void setServiceEvaluation(BigDecimal serviceEvaluation) {
        this.serviceEvaluation = serviceEvaluation;
    }

    public BigDecimal getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(BigDecimal evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent == null ? null : evaluationContent.trim();
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
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