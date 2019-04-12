package com.shopping.model.jsonbean;

import com.shopping.model.EmOdOrderDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangas on 2018/7/31.
 */
public class EmOdOrdersJsonBean {

    private String orderId;

    private String orderNo;

    private BigDecimal orderAmt;

    private Integer orderStatus;

    private Integer isAudited;

    private BigDecimal actualAmount;

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    List<EmOdOrderDetailJsonBean> emOdOrderDetailJsonBeanList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<EmOdOrderDetailJsonBean> getEmOdOrderDetailJsonBeanList() {
        return emOdOrderDetailJsonBeanList;
    }

    public void setEmOdOrderDetailJsonBeanList(List<EmOdOrderDetailJsonBean> emOdOrderDetailJsonBeanList) {
        this.emOdOrderDetailJsonBeanList = emOdOrderDetailJsonBeanList;
    }

    public Integer getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Integer isAudited) {
        this.isAudited = isAudited;
    }
}
