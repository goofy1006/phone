package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmGdGoods;
import com.shopping.model.EmGdGoodsWithBLOBs;
import java.util.List;

public interface EmGdGoodsMapper extends BaseDao {
    int deleteByPrimaryKey(String goodsId);

    int insert(EmGdGoodsWithBLOBs record);

    int insertSelective(EmGdGoodsWithBLOBs record);

    EmGdGoodsWithBLOBs selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(EmGdGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmGdGoodsWithBLOBs record);

    int updateByPrimaryKey(EmGdGoods record);

    List<EmGdGoods> selectList(EmGdGoods record);
}