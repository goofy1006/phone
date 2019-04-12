package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmSysDictionary;
import java.util.List;

public interface EmSysDictionaryMapper extends BaseDao {
    int deleteByPrimaryKey(Integer dictKey);

    int insert(EmSysDictionary record);

    int insertSelective(EmSysDictionary record);

    EmSysDictionary selectByPrimaryKey(Integer dictKey);

    int updateByPrimaryKeySelective(EmSysDictionary record);

    int updateByPrimaryKey(EmSysDictionary record);

    List<EmSysDictionary> selectList(EmSysDictionary record);
}