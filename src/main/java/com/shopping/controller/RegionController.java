package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmBbRegion;
import com.shopping.model.jsonbean.RegionJsonBean;
import com.shopping.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangas on 2018/7/26.
 */
@Api(tags = "省市区管理", value = "")
@RestController
@RequestMapping(value = "/region/")
public class RegionController extends BaseController{

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "查询省市区")
    @RequestMapping(value = "getAll.do", method = RequestMethod.POST)
    public APIResponse<List<RegionJsonBean>> region(){
        return APIResponse.success(regionService.selectAllRegion());
    }

}
