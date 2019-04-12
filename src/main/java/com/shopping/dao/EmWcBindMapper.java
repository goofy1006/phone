package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmWcBind;
import java.util.List;

public interface EmWcBindMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(EmWcBind record);

    int insertSelective(EmWcBind record);

    EmWcBind selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(EmWcBind record);

    int updateByPrimaryKey(EmWcBind record);

    List<EmWcBind> selectList(EmWcBind record);
}