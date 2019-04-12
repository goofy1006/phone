package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmOdOrderDetail;
import java.util.List;

public interface EmOdOrderDetailMapper extends BaseDao {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(EmOdOrderDetail record);

    int insertSelective(EmOdOrderDetail record);

    EmOdOrderDetail selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(EmOdOrderDetail record);

    int updateByPrimaryKey(EmOdOrderDetail record);

    List<EmOdOrderDetail> selectList(EmOdOrderDetail record);
}