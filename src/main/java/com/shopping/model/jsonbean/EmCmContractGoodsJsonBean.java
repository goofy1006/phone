package com.shopping.model.jsonbean;

import com.shopping.model.EmCmContractGoods;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2018/7/31.
 */
public class EmCmContractGoodsJsonBean extends EmCmContractGoods {

    private String goodsPic;//商品图片
    private String goodsDetail;//商品详情
    private  String attrNameValue;//商品规格json
    private  String gooodsDesc;//商品备注
    private BigDecimal priceB;//商品价格

    public String getGooodsDesc() {
        return gooodsDesc;
    }

    public void setGooodsDesc(String gooodsDesc) {
        this.gooodsDesc = gooodsDesc;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Override
    public String getAttrNameValue() {
        return attrNameValue;
    }

    @Override
    public void setAttrNameValue(String attrNameValue) {
        this.attrNameValue = attrNameValue;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }
}
