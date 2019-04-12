package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmTiUserTicket extends BaseDomain {
    private String id;

    private String ticketId;

    private String userId;

    private Date efstartTime;

    private Date efendTime;

    private Integer isUse;

    private Date useTime;

    private String orderNo;

    private BigDecimal deductionAmt;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId == null ? null : ticketId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getEfstartTime() {
        return efstartTime;
    }

    public void setEfstartTime(Date efstartTime) {
        this.efstartTime = efstartTime;
    }

    public Date getEfendTime() {
        return efendTime;
    }

    public void setEfendTime(Date efendTime) {
        this.efendTime = efendTime;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getDeductionAmt() {
        return deductionAmt;
    }

    public void setDeductionAmt(BigDecimal deductionAmt) {
        this.deductionAmt = deductionAmt;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}