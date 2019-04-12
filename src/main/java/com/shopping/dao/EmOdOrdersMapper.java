package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmOdOrders;
import java.util.List;

public interface EmOdOrdersMapper extends BaseDao {
    int deleteByPrimaryKey(String orderId);

    int insert(EmOdOrders record);

    int insertSelective(EmOdOrders record);

    EmOdOrders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(EmOdOrders record);

    int updateByPrimaryKey(EmOdOrders record);

    List<EmOdOrders> selectList(EmOdOrders record);
}