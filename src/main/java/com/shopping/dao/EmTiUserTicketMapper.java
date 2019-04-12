package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmTiUserTicket;
import java.util.List;

public interface EmTiUserTicketMapper extends BaseDao {
    int deleteByPrimaryKey(String id);

    int insert(EmTiUserTicket record);

    int insertSelective(EmTiUserTicket record);

    EmTiUserTicket selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmTiUserTicket record);

    int updateByPrimaryKey(EmTiUserTicket record);

    List<EmTiUserTicket> selectList(EmTiUserTicket record);
}