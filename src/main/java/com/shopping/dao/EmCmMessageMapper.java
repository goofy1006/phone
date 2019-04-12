package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmCmMessage;
import java.util.List;

public interface EmCmMessageMapper extends BaseDao {
    int deleteByPrimaryKey(String messageId);

    int insert(EmCmMessage record);

    int insertSelective(EmCmMessage record);

    EmCmMessage selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(EmCmMessage record);

    int updateByPrimaryKey(EmCmMessage record);

    List<EmCmMessage> selectList(EmCmMessage record);
}