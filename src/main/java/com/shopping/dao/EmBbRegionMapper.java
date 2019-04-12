package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmBbRegion;
import java.util.List;

public interface EmBbRegionMapper extends BaseDao {
    int deleteByPrimaryKey(String regionId);

    int insert(EmBbRegion record);

    int insertSelective(EmBbRegion record);

    EmBbRegion selectByPrimaryKey(String regionId);

    int updateByPrimaryKeySelective(EmBbRegion record);

    int updateByPrimaryKey(EmBbRegion record);

    List<EmBbRegion> selectList(EmBbRegion record);
}