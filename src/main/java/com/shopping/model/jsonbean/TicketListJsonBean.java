package com.shopping.model.jsonbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangas on 2018/8/12.
 */
public class TicketListJsonBean {

    private String userId;

    private String ticketId;

    private String ticketName;

    private Integer ticketType;

    private BigDecimal cost;

    //使用范围，1：全场通用、2：按品类、
    private Integer useScope;

    //使用条件，1：满xx元可用、2：无限制
    private Integer useCondition;

    private BigDecimal useConditionValue;

    private Integer validType;

    private Integer validVal;

    private Date efstartTime;

    private Date efendTime;

    private String categoryId;

    private String categoryName;

    //品类列表结合
    private String categoryNameAttrs;

    public String getCategoryNameAttrs() {
        return categoryNameAttrs;
    }

    public void setCategoryNameAttrs(String categoryNameAttrs) {
        this.categoryNameAttrs = categoryNameAttrs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
