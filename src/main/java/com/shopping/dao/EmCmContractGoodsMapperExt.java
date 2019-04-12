package com.shopping.dao;

import com.shopping.model.EmCmContractGoods;
import com.shopping.model.jsonbean.EmCmContractGoodsJsonBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface EmCmContractGoodsMapperExt extends EmCmContractGoodsMapper {

    List<EmCmContractGoodsJsonBean> selectListGoodsByCategoryId(String categroyId);

    EmCmContractGoodsJsonBean selectContractGoodsDetail(String goodsId);

    List<EmCmContractGoods> selectListByIds(String[] contractGoodsIds);

    List<EmCmContractGoodsJsonBean> selectListByContractGoodsName(Map<String, Object> map);
}