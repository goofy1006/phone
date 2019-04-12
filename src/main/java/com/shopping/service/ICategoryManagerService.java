package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmGdCategory;
import com.shopping.model.jsonbean.EmCmGdCategroyJsonBean;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2018/7/27.
 */
public interface ICategoryManagerService {

    /**
     * 分类信息查询
     * @param parentCategoryId
     * @return
     */
    public APIResponse<List<EmCmGdCategroyJsonBean>> selectByParentCategoryId(String parentCategoryId);

    /**
     * 查询2,3级分类信息
     * @param parentCategoryId
     * @return
     */
    public APIResponse<List<EmCmGdCategroyJsonBean>> selectTTCategory(String parentCategoryId);
}
