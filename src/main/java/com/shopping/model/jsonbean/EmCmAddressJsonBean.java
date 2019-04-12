package com.shopping.model.jsonbean;

import com.shopping.model.EmCmAddress;

/**
 * Created by lenovo on 2018/7/27.
 */
public class EmCmAddressJsonBean extends EmCmAddress {

    private String provinceName;//身份名称
    private String cityName;//城市名称
    private String countryName;//区域名称

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
