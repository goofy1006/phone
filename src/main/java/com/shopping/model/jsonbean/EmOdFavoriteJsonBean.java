package com.shopping.model.jsonbean;

import com.shopping.model.EmOdFavorite;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2018/8/1.
 */
public class EmOdFavoriteJsonBean extends EmOdFavorite {

    private String categoryId;
    private String categoryName;
    private BigDecimal goodsPrice;
    private String goodsPic;

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

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }
}
