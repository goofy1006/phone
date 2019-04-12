package com.shopping.model.jsonbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangas on 2018/8/11.
 */
public class TicketJsonBean {

    private String userId;

    private String ticketId;

    private Date efstartTime;

    private Date efendTime;

    private Integer isUse;

    private String ticketName;

    private Integer ticketType;

    private BigDecimal cost;

    //使用范围，1：全场通用、2：按品类、3：按店铺（暂无）、4：按商品（暂时）
    private Integer useScope;

    //使用条件，1：满xx元可用、2：无限制
    private Integer useCondition;

    //使用条件值，当为满xx元可用时指可用商品购买的金额
    private BigDecimal useConditionValue;

    //品类ID
    private String categoryId;

    //品类名称
    private String categoryName;

    //品类名称集合
    private String categoryNameAttrs;
//品类ID集合
    private String categoryIdAttrs;
//用户代金券ID
    private String userTicketId;

    public String getUserTicketId() {
        return userTicketId;
    }

    public void setUserTicketId(String userTicketId) {
        this.userTicketId = userTicketId;
    }

    public String getCategoryNameAttrs() {
        return categoryNameAttrs;
    }

    public void setCategoryNameAttrs(String categoryNameAttrs) {
        this.categoryNameAttrs = categoryNameAttrs;
    }

    public String getCategoryIdAttrs() {
        return categoryIdAttrs;
    }

    public void setCategoryIdAttrs(String categoryIdAttrs) {
        this.categoryIdAttrs = categoryIdAttrs;
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
