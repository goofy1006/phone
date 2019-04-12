package com.shopping.model.jsonbean;

import java.math.BigDecimal;

/**
 * Created by zhangas on 2018/8/12.
 */
public class AmountJsonBean {

    private BigDecimal totalAmount;

    private BigDecimal actualAmount;

    private BigDecimal discountAmpunt;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getDiscountAmpunt() {
        return discountAmpunt;
    }

    public void setDiscountAmpunt(BigDecimal discountAmpunt) {
        this.discountAmpunt = discountAmpunt;
    }
}
