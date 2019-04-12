package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.EmOdFavoriteJsonBean;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * Created by lenovo on 2018/8/1.
 */
public interface IEmOdFavoriteService {

    /**
     * 查询我的收藏信息
     * @param customerId
     * @param goodsId
     * @return
     */
    public APIResponse<List<EmOdFavoriteJsonBean>> selectListFavorite(String customerId,String goodsId);

    /**
     * 商品收藏删除
     * @param favoriteId
     * @return
     */
    public APIResponse deleFavorite(String favoriteIdArrs);

    /**
     * 商品收藏添加
     * @param customerId
     * @param goodsId
     * @return
     */
    public APIResponse addFavorite(String customerId,String goodsId);
}
