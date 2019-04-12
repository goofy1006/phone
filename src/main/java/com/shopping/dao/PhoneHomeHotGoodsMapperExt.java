package com.shopping.dao;

import com.shopping.model.PhoneHomeHotGoods;
import com.shopping.model.jsonbean.PhoneHomeHotGoodsJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PhoneHomeHotGoodsMapperExt extends PhoneHomeHotGoodsMapper {

    /**
     * 首页热门推荐
     * @return
     */
    List<PhoneHomeHotGoodsJsonBean> selectListHotGoods();
}