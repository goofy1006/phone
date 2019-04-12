package com.shopping.model.jsonbean;

import com.shopping.model.EmCmGdCategory;

import java.util.List;

/**
 * Created by lenovo on 2018/7/27.
 */
public class EmCmGdCategroyJsonBean extends EmCmGdCategory {

    private String categoryPic;//分类图片
    private List<EmCmGdCategroyJsonBean> TCategory;//三级分类信息

    @Override
    public String getCategoryPic() {
        return categoryPic;
    }

    @Override
    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    public List<EmCmGdCategroyJsonBean> getTCategory() {
        return TCategory;
    }

    public void setTCategory(List<EmCmGdCategroyJsonBean> TCategory) {
        this.TCategory = TCategory;
    }
}
