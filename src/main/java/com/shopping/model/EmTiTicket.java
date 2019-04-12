package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmTiTicket extends BaseDomain {
    private String ticketId;

    private Integer isIssue;

    private String ticketName;

    private Integer ticketType;

    private Integer issueTotal;

    private BigDecimal cost;

    private Integer issueWay;

    private Integer useScope;

    private Integer useCondition;

    private BigDecimal useConditionValue;

    private Integer limitCount;

    private Integer validType;

    private Integer validVal;

    private Date efstartTime;

    private Date efendTime;

    private Date sendStartTime;

    private Date sendEndTime;

    private Integer sendUser;

    private Integer drawCount;

    private Integer useCount;

    private BigDecimal amt;

    private String describes;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Integer version;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId == null ? null : ticketId.trim();
    }

    public Integer getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(Integer isIssue) {
        this.isIssue = isIssue;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getIssueTotal() {
        return issueTotal;
    }

    public void setIssueTotal(Integer issueTotal) {
        this.issueTotal = issueTotal;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getIssueWay() {
        return issueWay;
    }

    public void setIssueWay(Integer issueWay) {
        this.issueWay = issueWay;
    }

    public Integer getUseScope() {
        return useScope;
    }

    public void setUseScope(Integer useScope) {
        this.useScope = useScope;
    }

    public Integer getUseCondition() {
        return useCondition;
    }

    public void setUseCondition(Integer useCondition) {
        this.useCondition = useCondition;
    }

    public BigDecimal getUseConditionValue() {
        return useConditionValue;
    }

    public void setUseConditionValue(BigDecimal useConditionValue) {
        this.useConditionValue = useConditionValue;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getValidType() {
        return validType;
    }

    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    public Integer getValidVal() {
        return validVal;
    }

    public void setValidVal(Integer validVal) {
        this.validVal = validVal;
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

    public Date getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(Date sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public Date getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(Date sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public Integer getSendUser() {
        return sendUser;
    }

    public void setSendUser(Integer sendUser) {
        this.sendUser = sendUser;
    }

    public Integer getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(Integer drawCount) {
        this.drawCount = drawCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
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