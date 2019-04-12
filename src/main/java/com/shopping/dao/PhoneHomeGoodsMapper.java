package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneHomeGoods;
import java.util.List;

public interface PhoneHomeGoodsMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(PhoneHomeGoods record);

    int insertSelective(PhoneHomeGoods record);

    PhoneHomeGoods selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PhoneHomeGoods record);

    int updateByPrimaryKey(PhoneHomeGoods record);

    List<PhoneHomeGoods> selectList(PhoneHomeGoods record);
}