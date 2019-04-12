package com.shopping.model;

import cn.zjmiec.xks.common.plugin.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class EmGdGoods extends BaseDomain {
    private String goodsId;

    private String brandId;

    private String categoryId;

    private String storeId;

    private String storeName;

    private Integer goodsType;

    private String countyId;

    private String goodsCode;

    private String goodsName;

    private String keyWords;

    private String goodsPic;

    private String gooodsDesc;

    private Short upAct;

    private Short forbidSale;

    private String forbidCause;

    private Integer unit;

    private BigDecimal minPack;

    private String currentId;

    private BigDecimal priceA;

    private BigDecimal priceB;

    private BigDecimal priceC;

    private Long visitAmount;

    private BigDecimal salesVolume;

    private BigDecimal inventory;

    private BigDecimal score;

    private Short isLack;

    private Date onactTime;

    private Date offactTime;

    private Date forbidTime;

    private Short isInactive;

    private Date createdTime;

    private String createdBy;

    private Date updatedTime;

    private String updatedBy;

    private Long version;

    private String barcode;

    private String productArea;

    private String model;

    private String wareQd;

    private String supplierId;

    private String supplierName;

    private String sysGoodsCode;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
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

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public String getGooodsDesc() {
        return gooodsDesc;
    }

    public void setGooodsDesc(String gooodsDesc) {
        this.gooodsDesc = gooodsDesc == null ? null : gooodsDesc.trim();
    }

    public Short getUpAct() {
        return upAct;
    }

    public void setUpAct(Short upAct) {
        this.upAct = upAct;
    }

    public Short getForbidSale() {
        return forbidSale;
    }

    public void setForbidSale(Short forbidSale) {
        this.forbidSale = forbidSale;
    }

    public String getForbidCause() {
        return forbidCause;
    }

    public void setForbidCause(String forbidCause) {
        this.forbidCause = forbidCause == null ? null : forbidCause.trim();
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public BigDecimal getMinPack() {
        return minPack;
    }

    public void setMinPack(BigDecimal minPack) {
        this.minPack = minPack;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId == null ? null : currentId.trim();
    }

    public BigDecimal getPriceA() {
        return priceA;
    }

    public void setPriceA(BigDecimal priceA) {
        this.priceA = priceA;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }

    public BigDecimal getPriceC() {
        return priceC;
    }

    public void setPriceC(BigDecimal priceC) {
        this.priceC = priceC;
    }

    public Long getVisitAmount() {
        return visitAmount;
    }

    public void setVisitAmount(Long visitAmount) {
        this.visitAmount = visitAmount;
    }

    public BigDecimal getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(BigDecimal salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getInventory() {
        return inventory;
    }

    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Short getIsLack() {
        return isLack;
    }

    public void setIsLack(Short isLack) {
        this.isLack = isLack;
    }

    public Date getOnactTime() {
        return onactTime;
    }

    public void setOnactTime(Date onactTime) {
        this.onactTime = onactTime;
    }

    public Date getOffactTime() {
        return offactTime;
    }

    public void setOffactTime(Date offactTime) {
        this.offactTime = offactTime;
    }

    public Date getForbidTime() {
        return forbidTime;
    }

    public void setForbidTime(Date forbidTime) {
        this.forbidTime = forbidTime;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea == null ? null : productArea.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getWareQd() {
        return wareQd;
    }

    public void setWareQd(String wareQd) {
        this.wareQd = wareQd == null ? null : wareQd.trim();
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

    public String getSysGoodsCode() {
        return sysGoodsCode;
    }

    public void setSysGoodsCode(String sysGoodsCode) {
        this.sysGoodsCode = sysGoodsCode == null ? null : sysGoodsCode.trim();
    }
}