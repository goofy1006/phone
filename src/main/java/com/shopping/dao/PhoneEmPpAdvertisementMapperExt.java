package com.shopping.dao;

import com.shopping.model.PhoneEmPpAdvertisement;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PhoneEmPpAdvertisementMapperExt extends PhoneEmPpAdvertisementMapper {

    /**
     * 首页轮播图查询
     * @return
     */
    List<PhoneEmPpAdvertisement> selectListAdvertisement();
}