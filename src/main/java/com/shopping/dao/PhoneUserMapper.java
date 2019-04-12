package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneUser;
import java.util.List;

public interface PhoneUserMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(PhoneUser record);

    int insertSelective(PhoneUser record);

    PhoneUser selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PhoneUser record);

    int updateByPrimaryKey(PhoneUser record);

    List<PhoneUser> selectList(PhoneUser record);
}