package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmCmAddress;
import java.util.List;

public interface EmCmAddressMapper extends BaseDao {
    int deleteByPrimaryKey(String addressId);

    int insert(EmCmAddress record);

    int insertSelective(EmCmAddress record);

    EmCmAddress selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(EmCmAddress record);

    int updateByPrimaryKey(EmCmAddress record);

    List<EmCmAddress> selectList(EmCmAddress record);
}