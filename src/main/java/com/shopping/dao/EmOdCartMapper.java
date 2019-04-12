package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmOdCart;
import java.util.List;

public interface EmOdCartMapper extends BaseDao {
    int deleteByPrimaryKey(String cartId);

    int insert(EmOdCart record);

    int insertSelective(EmOdCart record);

    EmOdCart selectByPrimaryKey(String cartId);

    int updateByPrimaryKeySelective(EmOdCart record);

    int updateByPrimaryKey(EmOdCart record);

    List<EmOdCart> selectList(EmOdCart record);
}