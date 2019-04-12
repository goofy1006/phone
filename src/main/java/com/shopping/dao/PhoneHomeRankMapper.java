package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneHomeRank;
import java.util.List;

public interface PhoneHomeRankMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(PhoneHomeRank record);

    int insertSelective(PhoneHomeRank record);

    PhoneHomeRank selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PhoneHomeRank record);

    int updateByPrimaryKey(PhoneHomeRank record);

    List<PhoneHomeRank> selectList(PhoneHomeRank record);
}