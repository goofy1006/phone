package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneCompanyMessage;
import java.util.List;

public interface PhoneCompanyMessageMapper extends BaseDao {
    int deleteByPrimaryKey(Integer uuid);

    int insert(PhoneCompanyMessage record);

    int insertSelective(PhoneCompanyMessage record);

    PhoneCompanyMessage selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(PhoneCompanyMessage record);

    int updateByPrimaryKey(PhoneCompanyMessage record);

    List<PhoneCompanyMessage> selectList(PhoneCompanyMessage record);
}