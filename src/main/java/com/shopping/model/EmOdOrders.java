package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmOdOrders extends BaseDomain {
    private String orderId;

    private String contractCustomerId;

    private String customerId;

    private String orderNo;

    private String erpSoNo;

    private String storeId;

    private String storeName;

    private String costCenterId;

    private Integer orderType;

    private Integer orderStatus;

    private Integer oldOrderStatus;

    private Integer auditResult;

    private BigDecimal orderAmt;

    private String currentId;

    private String addressId;

    private String address;

    private String linkman;

    private String telephone;

    private String linkphone;

    private BigDecimal deliveryAmt;

    private Integer ladingWay;

    private Integer payWay;

    private Date orderDate;

    private Date payTime;

    private Date deliveryTime;

    private Integer deliveryPeriod;

    private Date receivedTime;

    private Short isPayback;

    private Date paybackTime;

    private BigDecimal paybackAmt;

    private String carrierName;

    private String wbNo;

    private String orderRemark;

    private String storeRemark;

    private String applyContent;

    private Integer invoiceType;

    private String invoiceTitle;

    private Short isAudited;

    private String orderAuditId;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Long version;

    private String province;

    private String city;

    private String country;

    private String invoiceId;

    private Integer pushtoerp;

    private Integer invoiceStatus;

    private Date deliveryAllTime;

    private Integer isErpcancel;

    private BigDecimal rate;

    private Date completeTime;

    private String outsideOrderno;

    private String supplierId;

    private String supplierName;

    private String payType;

    private String payFlag;

    private String payId;

    private String moneyReleaseState;

    private BigDecimal moneyRelease;

    private String marginPayFlag;

    private BigDecimal marginPayMoney;

    private BigDecimal invoiceAmountNotyet;

    private BigDecimal invoiceAmount;

    private Long isInvoiceEnd;

    private Integer isPingtai;

    private BigDecimal actualAmt;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getContractCustomerId() {
        return contractCustomerId;
    }

    public void setContractCustomerId(String contractCustomerId) {
        this.contractCustomerId = contractCustomerId == null ? null : contractCustomerId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getErpSoNo() {
        return erpSoNo;
    }

    public void setErpSoNo(String erpSoNo) {
        this.erpSoNo = erpSoNo == null ? null : erpSoNo.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId == null ? null : costCenterId.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOldOrderStatus() {
        return oldOrderStatus;
    }

    public void setOldOrderStatus(Integer oldOrderStatus) {
        this.oldOrderStatus = oldOrderStatus;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId == null ? null : currentId.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public BigDecimal getDeliveryAmt() {
        return deliveryAmt;
    }

    public void setDeliveryAmt(BigDecimal deliveryAmt) {
        this.deliveryAmt = deliveryAmt;
    }

    public Integer getLadingWay() {
        return ladingWay;
    }

    public void setLadingWay(Integer ladingWay) {
        this.ladingWay = ladingWay;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(Integer deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Short getIsPayback() {
        return isPayback;
    }

    public void setIsPayback(Short isPayback) {
        this.isPayback = isPayback;
    }

    public Date getPaybackTime() {
        return paybackTime;
    }

    public void setPaybackTime(Date paybackTime) {
        this.paybackTime = paybackTime;
    }

    public BigDecimal getPaybackAmt() {
        return paybackAmt;
    }

    public void setPaybackAmt(BigDecimal paybackAmt) {
        this.paybackAmt = paybackAmt;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName == null ? null : carrierName.trim();
    }

    public String getWbNo() {
        return wbNo;
    }

    public void setWbNo(String wbNo) {
        this.wbNo = wbNo == null ? null : wbNo.trim();
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public String getStoreRemark() {
        return storeRemark;
    }

    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark == null ? null : storeRemark.trim();
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent == null ? null : applyContent.trim();
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public Short getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Short isAudited) {
        this.isAudited = isAudited;
    }

    public String getOrderAuditId() {
        return orderAuditId;
    }

    public void setOrderAuditId(String orderAuditId) {
        this.orderAuditId = orderAuditId == null ? null : orderAuditId.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    public Integer getPushtoerp() {
        return pushtoerp;
    }

    public void setPushtoerp(Integer pushtoerp) {
        this.pushtoerp = pushtoerp;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getDeliveryAllTime() {
        return deliveryAllTime;
    }

    public void setDeliveryAllTime(Date deliveryAllTime) {
        this.deliveryAllTime = deliveryAllTime;
    }

    public Integer getIsErpcancel() {
        return isErpcancel;
    }

    public void setIsErpcancel(Integer isErpcancel) {
        this.isErpcancel = isErpcancel;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getOutsideOrderno() {
        return outsideOrderno;
    }

    public void setOutsideOrderno(String outsideOrderno) {
        this.outsideOrderno = outsideOrderno == null ? null : outsideOrderno.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag == null ? null : payFlag.trim();
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getMoneyReleaseState() {
        return moneyReleaseState;
    }

    public void setMoneyReleaseState(String moneyReleaseState) {
        this.moneyReleaseState = moneyReleaseState == null ? null : moneyReleaseState.trim();
    }

    public BigDecimal getMoneyRelease() {
        return moneyRelease;
    }

    public void setMoneyRelease(BigDecimal moneyRelease) {
        this.moneyRelease = moneyRelease;
    }

    public String getMarginPayFlag() {
        return marginPayFlag;
    }

    public void setMarginPayFlag(String marginPayFlag) {
        this.marginPayFlag = marginPayFlag == null ? null : marginPayFlag.trim();
    }

    public BigDecimal getMarginPayMoney() {
        return marginPayMoney;
    }

    public void setMarginPayMoney(BigDecimal marginPayMoney) {
        this.marginPayMoney = marginPayMoney;
    }

    public BigDecimal getInvoiceAmountNotyet() {
        return invoiceAmountNotyet;
    }

    public void setInvoiceAmountNotyet(BigDecimal invoiceAmountNotyet) {
        this.invoiceAmountNotyet = invoiceAmountNotyet;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Long getIsInvoiceEnd() {
        return isInvoiceEnd;
    }

    public void setIsInvoiceEnd(Long isInvoiceEnd) {
        this.isInvoiceEnd = isInvoiceEnd;
    }

    public Integer getIsPingtai() {
        return isPingtai;
    }

    public void setIsPingtai(Integer isPingtai) {
        this.isPingtai = isPingtai;
    }

    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }
}