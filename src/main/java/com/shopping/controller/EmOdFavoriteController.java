package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmOdFavorite;
import com.shopping.model.jsonbean.EmOdFavoriteJsonBean;
import com.shopping.service.IEmOdFavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2018/8/1.
 */
@Controller
@Api(tags = "我的收藏管理")
@RequestMapping(value = "/favorite/")
public class EmOdFavoriteController {

    @Autowired
    IEmOdFavoriteService iEmOdFavoriteService;

    /**
     * 我的收藏查询
     * @param customerId
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "我的收藏查询")
    @RequestMapping(value = "selectListFavorite.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmOdFavoriteJsonBean>> selectListFavorite(
            @ApiParam(value = "用户ID",name = "customerId")
            @RequestParam String customerId,
            @ApiParam(value = "商品ID",name = "goodsId")
            @RequestParam String goodsId
    ){
            return iEmOdFavoriteService.selectListFavorite(customerId,goodsId);
    }

    /**
     * 商品收藏删除
     * @param favoriteIdArrs
     * @return
     */
    @ApiOperation(value = "商品收藏删除")
    @RequestMapping(value = "deleFavorite.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse deleFavorite(
            @ApiParam(value = "收藏ID集合以，隔开",name = "favoriteIdArrs" )
            @RequestParam String favoriteIdArrs
    ){
            return iEmOdFavoriteService.deleFavorite(favoriteIdArrs);
    }

    /**
     * 商品收藏添加
     * @param customerId
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商品收藏添加")
    @RequestMapping(value = "addFavorite.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse addFavorite(
            @ApiParam(value = "用户ID",name = "customerId")
            @RequestParam String customerId,
            @ApiParam(value = "商品ID",name = "goodsId")
            @RequestParam String goodsId
    ){
            return iEmOdFavoriteService.addFavorite(customerId,goodsId);
    }
}
