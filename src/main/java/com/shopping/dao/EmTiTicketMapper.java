package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmTiTicket;
import java.util.List;

public interface EmTiTicketMapper extends BaseDao {
    int deleteByPrimaryKey(String ticketId);

    int insert(EmTiTicket record);

    int insertSelective(EmTiTicket record);

    EmTiTicket selectByPrimaryKey(String ticketId);

    int updateByPrimaryKeySelective(EmTiTicket record);

    int updateByPrimaryKey(EmTiTicket record);

    List<EmTiTicket> selectList(EmTiTicket record);
}