package com.shopping.dao;

import com.shopping.model.PhoneHomeGoods;
import com.shopping.model.jsonbean.PhoneHomeGoodsJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PhoneHomeGoodsMapperExt extends PhoneHomeGoodsMapper {

    /**
     * 首页分类信息查询
     * @return
     */
    List<PhoneHomeGoodsJsonBean> selectListHomeGoods(String categoryId);

    /**
     * 查询有多少分类
     * @return
     */
    List<String> selectCountCategory();
}