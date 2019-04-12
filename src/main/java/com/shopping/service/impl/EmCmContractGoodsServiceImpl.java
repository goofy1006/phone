package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.conmonUtil.client.EsUtil;
import com.shopping.conmonUtil.cont.Constant;
import com.shopping.conmonUtil.model.EsPage;
import com.shopping.conmonUtil.model.EsQueryCondition;
import com.shopping.dao.EmCmContractGoodsMapperExt;
import com.shopping.dao.EmCmGdCategoryMapperExt;
import com.shopping.dao.EmOdEvaluationMapperExt;
import com.shopping.dao.EmOdOrdersMapperExt;
import com.shopping.model.EmCmContractGoods;
import com.shopping.model.EmCmGdCategory;
import com.shopping.model.EmOdEvaluation;
import com.shopping.model.EmOdOrders;
import com.shopping.model.jsonbean.EmCmContractGoodsJsonBean;
import com.shopping.service.IEmCmContractGoodsService;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by lenovo on 2018/7/31.
 */
@Service
public class EmCmContractGoodsServiceImpl implements IEmCmContractGoodsService {

    @Value("${es_index_name}")
    private String es_index_name;

    @Value("${es_type_name}")
    private  String es_type_name;


    @Autowired
    EmCmContractGoodsMapperExt emCmContractGoodsMapperExt;
    @Autowired
    EmOdEvaluationMapperExt emOdEvaluationMapperExt;
    @Autowired
    EmOdOrdersMapperExt emOdOrdersMapperExt;

    @Autowired
    private EmCmGdCategoryMapperExt emCmGdCategoryMapperExt;

    @Override
    public APIResponse<List<EmCmContractGoodsJsonBean>> selectListGoodsByCategoryId(String categoryId,Integer pageSize,Integer pageNum,String sort) {
        APIResponse<List<EmCmContractGoodsJsonBean>> result = new APIResponse<>();
        if(StringUtils.isEmpty(sort)){
            sort = "a.created_time desc";
        }
        PageHelper.startPage(pageNum,pageSize,sort);
        List<EmCmContractGoodsJsonBean> list = emCmContractGoodsMapperExt.selectListGoodsByCategoryId(categoryId);
        PageInfo<EmCmContractGoodsJsonBean> pageInfo = new PageInfo<>(list);
        result.setCode(APIResponse.SUCCESS);
        result.setData(list);
        result.setTotal((int)pageInfo.getTotal());
        return result;
    }

    @Override
    public APIResponse<EmCmContractGoodsJsonBean> selectContractGoodsDetail(String goodsId) {
        EmCmContractGoodsJsonBean emCmContractGoodsJsonBean =  emCmContractGoodsMapperExt.selectContractGoodsDetail(goodsId);
        return APIResponse.success(emCmContractGoodsJsonBean);
    }

    @Override
    public APIResponse<List<EmOdEvaluation>> selectListEvaluation(String goodsId,Integer pageSize,Integer pageNum) {
        APIResponse<List<EmOdEvaluation>> result = new APIResponse<>();
        PageHelper.startPage(pageNum,pageSize,"created_time");
        EmOdEvaluation emOdEvaluation = new EmOdEvaluation();
        emOdEvaluation.setGoodsId(goodsId);
        List<EmOdEvaluation> list = emOdEvaluationMapperExt.selectList(emOdEvaluation);
        PageInfo<EmOdEvaluation> info = new PageInfo<>(list);
        result.setTotal((int)info.getTotal());
        result.setCode(APIResponse.SUCCESS);
        result.setData(list);
        return result;
    }

    @Override
    public APIResponse inserEvaluation(EmOdEvaluation emOdEvaluation,String orderId) {
        emOdEvaluation .setEvaluationId(CommonUtil.getUUID());
        emOdEvaluation.setCreatedTime(new Date());
        emOdEvaluation.setCreatedBy(emOdEvaluation.getUserCode());
        emOdEvaluation.setEvaluationTime(new Date());
        emOdEvaluation.setIsInactive((short)1);
        emOdEvaluationMapperExt.insertSelective(emOdEvaluation);
        EmOdOrders emOdOrders = new EmOdOrders();
        emOdOrders.setOrderId(orderId);
        emOdOrders.setIsAudited((short)1);//是否已评价：0：未评价 1：已评价
        emOdOrders.setUpdatedTime(new Date());
        emOdOrdersMapperExt.updateByPrimaryKeySelective(emOdOrders);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse selectListByContractGoodsName(String name) {

        //查询分类信息
        List<EmCmGdCategory> emCmGdCategories = emCmGdCategoryMapperExt.selectListByContractCustomerId();

        List<String> catagoryIds = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(emCmGdCategories)){
            for(EmCmGdCategory emCmGdCategory : emCmGdCategories){
                catagoryIds.add(emCmGdCategory.getCatgoryId());
            }
        }else{
            return APIResponse.success("pingtai1下面没有挂商品！");
        }
        Map<String ,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("list", catagoryIds);
        List<EmCmContractGoodsJsonBean> emCmContractGoodsJsonBeans = emCmContractGoodsMapperExt.selectListByContractGoodsName(map);
        return APIResponse.success(emCmContractGoodsJsonBeans);
    }

    @Override
    public Map<String, Object> selectGoodsFromEs(Map param) {
        Map<String, Object> result = new HashMap<String, Object>();
        EsPage page = prepareEsParam(param);


        // 如果是搜索类型为1品牌搜索 或者 品牌id不为空 则查询需关联品牌表
        if ("1".equals(param.get("SEARCH_TYPE"))
                || (null != param.get("BRAND_ID")) && !"".equals(param.get("BRAND_ID"))) {
            //result.put("DATA", goodsDao.getGoodsList_b(param));
            SearchResponse response = EsUtil.searchByBrand(page);

            List<Map<String, Object>> list = formatData(response.getHits().getHits());
            result.put("DATA", list);
            result.put("TOTAL", response.getHits().getTotalHits());//商品总数
        } else {// 否则查询商品，不包括品牌
            //result.put("DATA", goodsDao.getGoodsList_a(param));
            SearchResponse response = EsUtil.searchByGoods(page);

            List<Map<String, Object>> list = formatData(response.getHits().getHits());
            result.put("DATA", list);
            result.put("TOTAL", response.getHits().getTotalHits());//商品总数
        }

        //result.put("TOTAL", goodsDao.getGoodsRecords(param));//商品总数

        return result;
    }



    private EsPage prepareEsParam(Map<String, Object> param) {
        int pageIndex = Integer.parseInt(String.valueOf(param.get("PAGE_INDEX")));
        int pageSize = Integer.parseInt(String.valueOf(param.get("PAGE_SIZE")));

        //  Properties properties=PropertiesInfoUtil.getElasticSearchInfo();
        EsPage page = new EsPage();
        page.setPAGE_INDEX(pageIndex * pageSize);
        page.setPAGE_SIZE(pageSize);
        page.setIndex(es_index_name);
        page.setType(es_type_name);

        // 如果存在排序
        if (null != param.get("SORT_NAME")
                && !"".equals(param.get("SORT_NAME"))
                && null != param.get("SORT_TYPE")
                && !"".equals(param.get("SORT_TYPE"))) {
            Map<String, String> sortMap = new HashMap<String, String>();
            sortMap.put(String.valueOf(param.get("SORT_NAME")), String.valueOf(param.get("SORT_TYPE")));
            page.setSortMap(sortMap);
        }


        List<EsQueryCondition> condition = new ArrayList<EsQueryCondition>();
        // 合约客户id
        EsQueryCondition custCondtion = new EsQueryCondition(
                "CONTRACT_CUSTOMER_ID", Constant.QUERY_EQUAL,
                String.valueOf(param.get("CONTRACT_CUSTOMER_ID")), Constant.OPER_AND);
        condition.add(custCondtion);

        if(null != param.get("BRAND_ID") && !"".equals(param.get("BRAND_ID"))){//品牌id
            EsQueryCondition contractedCondtion = new EsQueryCondition(
                    "BRAND_ID", Constant.QUERY_EQUAL,
                    String.valueOf(param.get("BRAND_ID")), Constant.OPER_AND);
            condition.add(contractedCondtion);
        }
        if(null != param.get("CATEGORY_TREE_CODE") && !"".equals(param.get("CATEGORY_TREE_CODE"))){//树状查询码
            EsQueryCondition contractedCondtion = new EsQueryCondition(
                    "CATEGORY_TREE_CODE", Constant.QUERY_PREFIX,
                    String.valueOf(param.get("CATEGORY_TREE_CODE")), Constant.OPER_AND);
            condition.add(contractedCondtion);
        }
        if(null != param.get("SEARCH_CONTENT") && !"".equals(param.get("SEARCH_CONTENT"))){//查询内容
            EsQueryCondition contractedCondtion = new EsQueryCondition(
                    "SEARCH_CONTENT", Constant.QUERY_EQUAL,
                    String.valueOf(param.get("SEARCH_CONTENT")), Constant.OPER_AND);
            condition.add(contractedCondtion);
        }
        page.setCondition(condition);
        return page;
    }

    private List<Map<String, Object>> formatData(SearchHit[] hits){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < hits.length; i++) {
            SearchHit hit = hits[i];
            Map<String, Object> source = hit.getSource();
            list.add(source);
        }
        return list;
    }

}
