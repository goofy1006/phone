package com.shopping.dao;

import com.shopping.model.jsonbean.EmOdFavoriteJsonBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface EmOdFavoriteMapperExt extends EmOdFavoriteMapper {

    List<EmOdFavoriteJsonBean> selectListFavorite(Map<String,Object> map);
}