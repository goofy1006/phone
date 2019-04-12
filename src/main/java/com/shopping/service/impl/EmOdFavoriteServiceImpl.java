package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.dao.EmOdFavoriteMapperExt;
import com.shopping.model.EmOdFavorite;
import com.shopping.model.jsonbean.EmOdFavoriteJsonBean;
import com.shopping.service.IEmOdFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/8/1.
 */
@Service
public class EmOdFavoriteServiceImpl implements IEmOdFavoriteService {
    @Autowired
    EmOdFavoriteMapperExt emOdFavoriteMapperExt;

    @Override
    public APIResponse addFavorite(String customerId, String goodsId) {
        EmOdFavorite emOdFavorite = new EmOdFavorite();
        emOdFavorite.setFavoriteId(CommonUtil.getUUID());
        emOdFavorite.setCustomerId(customerId);
        emOdFavorite.setGoodsId(goodsId);
        emOdFavoriteMapperExt.insertSelective(emOdFavorite);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse deleFavorite(String favoriteIdArrs) {
        String favoriteIds[] = favoriteIdArrs.split(",");
        for(int i=0;i<favoriteIds.length;i++){
            emOdFavoriteMapperExt.deleteByPrimaryKey(favoriteIds[i]);
        }
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse<List<EmOdFavoriteJsonBean>> selectListFavorite(String customerId, String goodsId) {
        Map<String,Object> map = new HashMap<>();
        map.put("customerId",customerId);
        map.put("goodsId",goodsId);
        List<EmOdFavoriteJsonBean> list = emOdFavoriteMapperExt.selectListFavorite(map);
        return APIResponse.success(list);
    }
}
