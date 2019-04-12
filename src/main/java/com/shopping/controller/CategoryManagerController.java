package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmContractGoods;
import com.shopping.model.EmCmGdCategory;
import com.shopping.model.jsonbean.EmCmContractGoodsJsonBean;
import com.shopping.model.jsonbean.EmCmGdCategroyJsonBean;
import com.shopping.service.ICategoryManagerService;
import com.shopping.service.IEmCmContractGoodsService;
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
 * Created by lenovo on 2018/7/27.
 */
@Controller
@Api(tags = "分类信息管理")
@RequestMapping(value = "/category/")
public class CategoryManagerController {

    @Autowired
    ICategoryManagerService iCategoryManagerService;
    @Autowired
    IEmCmContractGoodsService iEmCmContractGoodsService;

    /**
     * 分类信息查询
     * @param parentCategoryId
     * @return
     */
    @ApiOperation(value = "1分类信息查询")
    @RequestMapping(value = "selectByParentCategoryId.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmCmGdCategroyJsonBean>> selectByParentCategoryId(
            @ApiParam(value = "分类ID",name = "parentCategoryId",required = true)
            @RequestParam String parentCategoryId){
            return iCategoryManagerService.selectByParentCategoryId(parentCategoryId);
    }

    /**
     * 2,3级分类信息查询
     * @return
     */
    @ApiOperation(value = "2,3分类信息查询")
    @RequestMapping(value = "selectTTCategory.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmCmGdCategroyJsonBean>> selectTTCategory(
            @ApiParam(value = "分类ID",name = "parentCategoryId",required = true)
            @RequestParam String parentCategoryId
    ){
        return iCategoryManagerService.selectTTCategory(parentCategoryId);
    }

    /**
     * 根据分类ID查询商品信息
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "根据分类ID查询商品信息",notes = "销量排序倒序：a.sales_account desc  升序：asc |价格排序：a.contract_price |新品排序：a.created_time")
    @RequestMapping(value = "selectListGoodsByCategoryId.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmCmContractGoodsJsonBean>> selectListGoodsByCategoryId(
          @ApiParam(value = "分类ID",name = "categoryId")
          @RequestParam  String categoryId,
          @ApiParam(value = "每页显示条数",name = "pageSize")
          @RequestParam Integer pageSize,
          @ApiParam(value = "页码",name = "pageNum")
          @RequestParam Integer pageNum,
          @ApiParam(value = "排序",name = "sort")
          @RequestParam String sort
    ){
        return iEmCmContractGoodsService.selectListGoodsByCategoryId(categoryId,pageSize,pageNum,sort);
    }

    @ApiOperation(value = "首页搜索框")
    @RequestMapping(value = "selectListByContractGoodsName", method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmCmContractGoodsJsonBean>>  selectListByContractGoodsName(
            @ApiParam(value = "商品名称",name = "contractGoodsName")
            @RequestParam  String contractGoodsName
    ){
        return iEmCmContractGoodsService.selectListByContractGoodsName(contractGoodsName);
    }
}
