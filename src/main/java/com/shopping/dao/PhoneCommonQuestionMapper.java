package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneCommonQuestion;
import java.util.List;

public interface PhoneCommonQuestionMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(PhoneCommonQuestion record);

    int insertSelective(PhoneCommonQuestion record);

    PhoneCommonQuestion selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PhoneCommonQuestion record);

    int updateByPrimaryKey(PhoneCommonQuestion record);

    List<PhoneCommonQuestion> selectList(PhoneCommonQuestion record);
}