package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.*;
import com.shopping.model.jsonbean.PhoneHomeGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeGoodsResultJsonBean;
import com.shopping.model.jsonbean.PhoneHomeHotGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeRankJsonBean;

import java.util.List;

/**
 * Created by lenovo on 2018/7/23.
 */
public interface IPhoneHomePageService {

    /**
     * 首页轮播图查询
     * @return
     */
    public APIResponse<List<PhoneEmPpAdvertisement>> selectListAdvertisement();

    /**
     * 首页头条配置查询
     * @return
     */
    public APIResponse<List<PhoneEmPpNotice>> selectListNotice();

    /**
     * 首页排行榜查询
     * @return
     */
    public APIResponse<List<PhoneHomeRankJsonBean>> selectListRank();

    /**
     * 首页热门推荐
     * @return
     */
    public APIResponse<List<PhoneHomeHotGoodsJsonBean>> selectListHotGoods();

    /**
     * 首页商品分类表
     * @return
     */
    public APIResponse<List<PhoneHomeGoodsResultJsonBean>> selectListHomeGoods();

}
