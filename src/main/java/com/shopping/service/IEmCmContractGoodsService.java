package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmContractGoods;
import com.shopping.model.EmOdEvaluation;
import com.shopping.model.jsonbean.EmCmContractGoodsJsonBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/31.
 */
public interface IEmCmContractGoodsService {

    /**
     * 根据分类ID查询商品信息
     * @param categoryId
     * @return
     */
    public APIResponse<List<EmCmContractGoodsJsonBean>> selectListGoodsByCategoryId(String categoryId,Integer pageSize,Integer pageNum,String sort);

    /**
     * 根据商品ID查询商品明细
     * @param goodsId
     * @return
     */
    public APIResponse<EmCmContractGoodsJsonBean> selectContractGoodsDetail(String goodsId);

    public APIResponse<List<EmOdEvaluation>> selectListEvaluation(String goodsId,Integer pageSize,Integer pageNum);

    public APIResponse inserEvaluation(EmOdEvaluation emOdEvaluation,String orderId);

    APIResponse selectListByContractGoodsName(String name);

    Map<String,Object> selectGoodsFromEs(Map param);
}
