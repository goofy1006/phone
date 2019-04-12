package com.shopping.model.jsonbean;

import java.util.List;

/**
 * Created by lenovo on 2018/8/21.
 */
public class PhoneHomeGoodsResultJsonBean {

    String categoryName;
    String categoryId;
    List<PhoneHomeGoodsJsonBean> phoneHomeGoodsJsonBeans;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<PhoneHomeGoodsJsonBean> getPhoneHomeGoodsJsonBeans() {
        return phoneHomeGoodsJsonBeans;
    }

    public void setPhoneHomeGoodsJsonBeans(List<PhoneHomeGoodsJsonBean> phoneHomeGoodsJsonBeans) {
        this.phoneHomeGoodsJsonBeans = phoneHomeGoodsJsonBeans;
    }
}
