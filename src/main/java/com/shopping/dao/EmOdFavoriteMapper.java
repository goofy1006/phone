package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmOdFavorite;
import java.util.List;

public interface EmOdFavoriteMapper extends BaseDao {
    int deleteByPrimaryKey(String favoriteId);

    int insert(EmOdFavorite record);

    int insertSelective(EmOdFavorite record);

    EmOdFavorite selectByPrimaryKey(String favoriteId);

    int updateByPrimaryKeySelective(EmOdFavorite record);

    int updateByPrimaryKey(EmOdFavorite record);

    List<EmOdFavorite> selectList(EmOdFavorite record);
}