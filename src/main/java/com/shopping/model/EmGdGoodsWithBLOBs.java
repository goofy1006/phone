package com.shopping.model;

public class EmGdGoodsWithBLOBs extends EmGdGoods {
    private String goodsDetail;

    private String attrNameValue;

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail == null ? null : goodsDetail.trim();
    }

    public String getAttrNameValue() {
        return attrNameValue;
    }

    public void setAttrNameValue(String attrNameValue) {
        this.attrNameValue = attrNameValue == null ? null : attrNameValue.trim();
    }
}