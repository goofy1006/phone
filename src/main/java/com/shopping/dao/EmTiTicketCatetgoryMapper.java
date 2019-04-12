package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmTiTicketCatetgory;
import java.util.List;

public interface EmTiTicketCatetgoryMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(EmTiTicketCatetgory record);

    int insertSelective(EmTiTicketCatetgory record);

    EmTiTicketCatetgory selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(EmTiTicketCatetgory record);

    int updateByPrimaryKey(EmTiTicketCatetgory record);

    List<EmTiTicketCatetgory> selectList(EmTiTicketCatetgory record);
}