package com.shopping.dao;

import com.shopping.model.EmOdOrderDetail;
import com.shopping.model.jsonbean.EmOdOrderDetailJsonBean;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface EmOdOrderDetailMapperExt extends EmOdOrderDetailMapper {

    List<EmOdOrderDetailJsonBean> selectByOrderId(@Param(value = "orderId") String orderId);

    //查询明细商品ID
    List<EmOdOrderDetailJsonBean> goodsIdList(String orderNo);

    //更新商品已出售数量
    int updateGoodsSalesVol(String goodsId);

}