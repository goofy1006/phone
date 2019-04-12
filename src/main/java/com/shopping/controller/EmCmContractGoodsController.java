package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.shopping.model.EmOdEvaluation;
import com.shopping.model.jsonbean.EmCmContractGoodsJsonBean;
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
import java.util.Map;

/**
 * Created by lenovo on 2018/7/31.
 */
@Controller
@Api(tags = "商品管理")
@RequestMapping(value = "/contractGoods/")
public class EmCmContractGoodsController {

    @Autowired
    IEmCmContractGoodsService iEmCmContractGoodsService;

    /**
     * 根据商品ID查询商品明细
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "根据商品ID查询商品明细")
    @RequestMapping(value = "selectContractGoodsDetail.do",method = RequestMethod.POST)
    public @ResponseBody  APIResponse<EmCmContractGoodsJsonBean> selectContractGoodsDetail(
            @ApiParam(value = "商品ID",name = "goodsId")
            @RequestParam String goodsId
    ){
            return iEmCmContractGoodsService.selectContractGoodsDetail(goodsId);
    }

    /**
     * 查询商品评价
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "查询商品评价")
    @RequestMapping(value = "selectListEvaluation.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmOdEvaluation>> selectListEvaluation(
            @ApiParam(value = "商品ID",name = "goodsId")
            @RequestParam String goodsId,
            @ApiParam(value = "每页显示条数",name = "pageSize")
            @RequestParam Integer pageSize,
            @ApiParam(value = "页码",name = "pageNum")
            @RequestParam Integer pageNum
    ){
        return iEmCmContractGoodsService.selectListEvaluation(goodsId,pageSize,pageNum);
    }

    /**
     * 商品评价插入
     * @param customerId
     * @param orderDetailId
     * @param goodsId
     * @param userCode
     * @param evaluationContent
     * @return
     */
    @ApiOperation(value = "商品评价插入")
    @RequestMapping(value = "inserEvaluation.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse inserEvaluation(
            @ApiParam(value = "用户ID",name ="customerId" )
            @RequestParam String customerId,
            @ApiParam(value = "订单明细ID",name ="orderDetailId" )
            @RequestParam String orderDetailId,
            @ApiParam(value = "商品ID",name ="goodsId" )
            @RequestParam String goodsId,
            @ApiParam(value = "客户用户名",name ="userCode" )
            @RequestParam String userCode,
            @ApiParam(value = "评价内容",name ="evaluationContent" )
            @RequestParam String evaluationContent,
            @ApiParam(value = "订单编号",name ="orderId" )
            @RequestParam String orderId
    ){
        EmOdEvaluation emOdEvaluation = new EmOdEvaluation();
        if(!StringUtils.isEmpty(customerId)){
            emOdEvaluation.setCustomerId(customerId);
        }
        if(!StringUtils.isEmpty(orderDetailId)){
            emOdEvaluation.setOrderDetailId(orderDetailId);
        }
        if(!StringUtils.isEmpty(goodsId)){
            emOdEvaluation.setGoodsId(goodsId);
        }
        if(!StringUtils.isEmpty(userCode)){
            emOdEvaluation.setUserCode(userCode);
        }
        if(!StringUtils.isEmpty(evaluationContent)){
            emOdEvaluation.setEvaluationContent(evaluationContent);
        }
        return iEmCmContractGoodsService.inserEvaluation(emOdEvaluation,orderId);
    }

    /**
     * 商品查询
     * @param data
     * @return
     */

    @ApiOperation(value = "商品查询")
    @RequestMapping(value = "selectGoodsFromEs.do", method = RequestMethod.POST)
    public @ResponseBody APIResponse selectGoodsFromEs(@ApiParam(value = "查询商品参数", name = "data"  )
                                                       @RequestParam String data) {
        JSONObject jsonObject = null;
        Map param=null;
        try {
            param = JSONObject.parseObject(data);
        } catch (Exception e) {
            return APIResponse.fail("参数格式解析异常！");
        }
        String searchContent = (String)param.get("SEARCH_CONTENT");
        String contract_customer_id = (String)param.get("CONTRACT_CUSTOMER_ID");
        param.put("SEARCH_CONTENT",searchContent.replace("　",""));
        param.put("CONTRACT_CUSTOMER_ID", contract_customer_id);
        Map<String, Object> map = iEmCmContractGoodsService.selectGoodsFromEs(param);
        return APIResponse.success(map);
    }

}
