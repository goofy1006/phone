package com.shopping.model.jsonbean;

/**
 * Created by lenovo on 2018/8/6.
 */
public class PayModelBackJsonBean {

    private String order_no;
    private String pay_status;
    private String pay_msg;
    private String trade_sn;
    private String total_fee;
    private String sign;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getPay_msg() {
        return pay_msg;
    }

    public void setPay_msg(String pay_msg) {
        this.pay_msg = pay_msg;
    }

    public String getTrade_sn() {
        return trade_sn;
    }

    public void setTrade_sn(String trade_sn) {
        this.trade_sn = trade_sn;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
