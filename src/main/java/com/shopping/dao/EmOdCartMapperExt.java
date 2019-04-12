package com.shopping.dao;

import com.shopping.model.jsonbean.EmOdCartJsonBean;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Resource
public interface EmOdCartMapperExt extends EmOdCartMapper {

    public List<EmOdCartJsonBean> selectListCart(Map<String,Object> map);

    public void editCartGoodsNum(Map<String,Object> map);
}