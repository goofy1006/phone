package com.shopping.service.impl;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.dao.EmCmGdCategoryMapperExt;
import com.shopping.model.EmCmGdCategory;
import com.shopping.model.jsonbean.EmCmGdCategroyJsonBean;
import com.shopping.service.ICategoryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/7/27.
 */
@Service
public class CategoryManagerServiceImpl implements ICategoryManagerService {

    @Autowired
    EmCmGdCategoryMapperExt emCmGdCategoryMapperExt;

    @Override
    public APIResponse<List<EmCmGdCategroyJsonBean>> selectByParentCategoryId(String parentCategoryId) {
        EmCmGdCategory emCmGdCategory = new EmCmGdCategory();
        emCmGdCategory.setParentCategoryId(parentCategoryId);
        emCmGdCategory.setIsShow((short)0);//显示
        emCmGdCategory.setIsInactive((short)0);//启用
        List<EmCmGdCategroyJsonBean> list = emCmGdCategoryMapperExt.selectListByParentId(emCmGdCategory);
        if(list != null && !list.isEmpty()){
                for(Integer i=0;i<list.size();i++){//当为三级分类时，查询商品图片，取第一个商品的图片
                    if(list.get(i).getCategoryTreeCode().length() == 9){
                        List<String> picList = emCmGdCategoryMapperExt.selectGoodsPic(list.get(i).getCatgoryId());
                        if(picList != null && !picList.isEmpty()){
                            list.get(i).setCategoryPic(picList.get(0));
                        }
                    }
                }
        }
        return APIResponse.success(list);
    }

    @Override
    public APIResponse<List<EmCmGdCategroyJsonBean>> selectTTCategory(String parentCategoryId) {
        EmCmGdCategory emCmGdCategory = new EmCmGdCategory();
        emCmGdCategory.setParentCategoryId(parentCategoryId);
        emCmGdCategory.setIsShow((short)0);//显示
        emCmGdCategory.setIsInactive((short)0);//启用
        List<EmCmGdCategroyJsonBean> list = emCmGdCategoryMapperExt.selectListByParentId(emCmGdCategory);
        if(list != null && !list.isEmpty()){
            for(Integer i=0;i<list.size();i++){//当为三级分类时，查询商品图片，取第一个商品的图片
                emCmGdCategory.setParentCategoryId(list.get(i).getCatgoryId());
                List<EmCmGdCategroyJsonBean> listThree = emCmGdCategoryMapperExt.selectListByParentId(emCmGdCategory);//三级类
                    if(listThree != null && !listThree.isEmpty()){
                           for(Integer j = 0;j<listThree.size();j++){
                               List<String> picList = emCmGdCategoryMapperExt.selectGoodsPic(listThree.get(j).getCatgoryId());
                               if(picList != null && !picList.isEmpty()){
                                   listThree.get(j).setCategoryPic(picList.get(0));
                               }
                           }
                    }
                list.get(i).setTCategory(listThree);
            }
        }

        return APIResponse.success(list);
    }
}
