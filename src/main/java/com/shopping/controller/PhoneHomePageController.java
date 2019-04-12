package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.*;
import com.shopping.model.jsonbean.PhoneHomeGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeGoodsResultJsonBean;
import com.shopping.model.jsonbean.PhoneHomeHotGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeRankJsonBean;
import com.shopping.service.IPhoneHomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2018/7/23.
 */
@Api(tags = "手机首页信息查询",value = "")
@RequestMapping(value = "/phone/")
@Controller
public class PhoneHomePageController {
    @Autowired
    IPhoneHomePageService iPhoneHomePageService;

    /**
     * 首页轮播图查询
     * @return
     */
    @ApiOperation(value = "首页轮播图查询", notes = "")
    @RequestMapping(value = "selectListAdvertisement.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneEmPpAdvertisement>> selectListAdvertisement(){
        return iPhoneHomePageService.selectListAdvertisement();
    }

    /**
     * 首页头条配置查询
     * @return
     */
    @ApiOperation(value = "首页头条配置查询", notes = "")
    @RequestMapping(value = "selectListNotice.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneEmPpNotice>> selectListNotice(){
        return iPhoneHomePageService.selectListNotice();
    }

    /**
     * 首页排行榜查询
     * @return
     */
    @ApiOperation(value = "首页排行榜查询", notes = "")
    @RequestMapping(value = "selectListRank.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneHomeRankJsonBean>> selectListRank(){
        return iPhoneHomePageService.selectListRank();
    }

    /**
     * 首页热门推荐
     * @return
     */
    @ApiOperation(value = "首页热门推荐", notes = "")
    @RequestMapping(value = "selectListHotGoods.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneHomeHotGoodsJsonBean>> selectListHotGoods(){
        return iPhoneHomePageService.selectListHotGoods();
    }

    /**
     * 首页商品分类表
     * @return
     */
    @ApiOperation(value = "首页商品分类表", notes = "")
    @RequestMapping(value = "selectListHomeGoods.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneHomeGoodsResultJsonBean>> selectListHomeGoods(){
        return iPhoneHomePageService.selectListHomeGoods();
    }
}
