package com.shopping.model;

import java.math.BigDecimal;
import java.util.Date;

public class EmCmGdCategory extends EmCmGdCategoryKey {
    private String parentCategoryId;

    private String categoryTreeCode;

    private String categoryCode;

    private String categoryName;

    private String categoryPic;

    private String categoryDesc;

    private Long categoryOrder;

    private Short isShow;

    private Short isInactive;

    private BigDecimal discount;

    private Date createdTime;

    private String createdBy;

    private Date updatedTime;

    private String updatedBy;

    private Long version;

    private Short isRepair;

    private String supplierId;

    private String supplierName;

    private Short coverDiscount;

    private String floorAdvert;

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId == null ? null : parentCategoryId.trim();
    }

    public String getCategoryTreeCode() {
        return categoryTreeCode;
    }

    public void setCategoryTreeCode(String categoryTreeCode) {
        this.categoryTreeCode = categoryTreeCode == null ? null : categoryTreeCode.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic == null ? null : categoryPic.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public Long getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Long categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public Short getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Short isInactive) {
        this.isInactive = isInactive;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public Short getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Short isRepair) {
        this.isRepair = isRepair;
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

    public Short getCoverDiscount() {
        return coverDiscount;
    }

    public void setCoverDiscount(Short coverDiscount) {
        this.coverDiscount = coverDiscount;
    }

    public String getFloorAdvert() {
        return floorAdvert;
    }

    public void setFloorAdvert(String floorAdvert) {
        this.floorAdvert = floorAdvert == null ? null : floorAdvert.trim();
    }
}