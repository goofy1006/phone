package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneHomeHotGoods;
import java.util.List;

public interface PhoneHomeHotGoodsMapper extends BaseDao {
    int deleteByPrimaryKey(String uuid);

    int insert(PhoneHomeHotGoods record);

    int insertSelective(PhoneHomeHotGoods record);

    PhoneHomeHotGoods selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PhoneHomeHotGoods record);

    int updateByPrimaryKey(PhoneHomeHotGoods record);

    List<PhoneHomeHotGoods> selectList(PhoneHomeHotGoods record);
}