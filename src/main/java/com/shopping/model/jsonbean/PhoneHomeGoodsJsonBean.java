package com.shopping.model.jsonbean;

import com.shopping.model.PhoneHomeGoods;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2018/7/24.
 */
public class PhoneHomeGoodsJsonBean extends PhoneHomeGoods {

    private String categoryName;//分类名称
    private String contractGoodsName;//合约商品名称
    private BigDecimal contractPrice;//合约商品价格
    private  BigDecimal priceB;// 平台商品价格B
    private  String goodsPic;//商品首页图片

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContractGoodsName() {
        return contractGoodsName;
    }

    public void setContractGoodsName(String contractGoodsName) {
        this.contractGoodsName = contractGoodsName;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }
}
