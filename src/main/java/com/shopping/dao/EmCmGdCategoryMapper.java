package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmCmGdCategory;
import com.shopping.model.EmCmGdCategoryKey;
import java.util.List;

public interface EmCmGdCategoryMapper extends BaseDao {
    int deleteByPrimaryKey(EmCmGdCategoryKey key);

    int insert(EmCmGdCategory record);

    int insertSelective(EmCmGdCategory record);

    EmCmGdCategory selectByPrimaryKey(EmCmGdCategoryKey key);

    int updateByPrimaryKeySelective(EmCmGdCategory record);

    int updateByPrimaryKeyWithBLOBs(EmCmGdCategory record);

    int updateByPrimaryKey(EmCmGdCategory record);

    List<EmCmGdCategory> selectList(EmCmGdCategory record);
}