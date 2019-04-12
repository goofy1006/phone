package com.shopping.dao;

import com.shopping.model.EmCmGdCategory;
import com.shopping.model.jsonbean.EmCmGdCategroyJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface EmCmGdCategoryMapperExt extends EmCmGdCategoryMapper {

    /**
     * 根据类名查询商品图片
     * @param categoryId
     * @return
     */
    List<String> selectGoodsPic(String categoryId);

    /**
     * 根据父ID查询分类信息
     * @param record
     * @return
     */
    List<EmCmGdCategroyJsonBean> selectListByParentId(EmCmGdCategory record);

    List<EmCmGdCategory> selectListByContractCustomerId();
}