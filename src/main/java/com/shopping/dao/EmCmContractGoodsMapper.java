package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.EmCmContractGoods;
import java.util.List;

public interface EmCmContractGoodsMapper extends BaseDao {
    int deleteByPrimaryKey(String contractGoodsId);

    int insert(EmCmContractGoods record);

    int insertSelective(EmCmContractGoods record);

    EmCmContractGoods selectByPrimaryKey(String contractGoodsId);

    int updateByPrimaryKeySelective(EmCmContractGoods record);

    int updateByPrimaryKeyWithBLOBs(EmCmContractGoods record);

    int updateByPrimaryKey(EmCmContractGoods record);

    List<EmCmContractGoods> selectList(EmCmContractGoods record);
}