package com.shopping.model.jsonbean;

import java.math.BigDecimal;

/**
 * Created by zhangas on 2018/8/1.
 */
public class EmOdOrderDetailJsonBean {

    private String goodsId;

    private BigDecimal count;  //商品数量

    private BigDecimal salesPrice; //销售单价

    private String contractGoodsName;//合约商品名称

    private String goodsPic;

    private String orderDetailId;//商品明细ID

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getContractGoodsName() {
        return contractGoodsName;
    }

    public void setContractGoodsName(String contractGoodsName) {
        this.contractGoodsName = contractGoodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
