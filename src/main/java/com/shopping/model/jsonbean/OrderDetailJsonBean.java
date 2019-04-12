package com.shopping.model.jsonbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangas on 2018/8/17.
 */
public class OrderDetailJsonBean {

    private String orderNo;

    private Date orderDate;

    private Integer payWay;

    private String payWayDescribe;

    private Date payTime;

    private String linkman;

    private String linkphone;

    private String address;

    private BigDecimal orderAmt;

    private BigDecimal actualAmt;

    private BigDecimal discountAmpunt;

    private String carrierName;

    private String wbNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getPayWayDescribe() {
        return "在线支付";
    }

    public void setPayWayDescribe(String payWayDescribe) {
        this.payWayDescribe = payWayDescribe;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }

    public BigDecimal getDiscountAmpunt() {
        return orderAmt.compareTo(actualAmt) < 0 ? new BigDecimal(0) : orderAmt.subtract(actualAmt);
    }

    public void setDiscountAmpunt(BigDecimal discountAmpunt) {
        this.discountAmpunt = discountAmpunt;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getWbNo() {
        return wbNo;
    }

    public void setWbNo(String wbNo) {
        this.wbNo = wbNo;
    }
}
