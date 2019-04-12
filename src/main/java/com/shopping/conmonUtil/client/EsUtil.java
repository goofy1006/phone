package com.shopping.conmonUtil.client;

import com.shopping.conmonUtil.JsonUtils;
import com.shopping.conmonUtil.PropertiesInfoUtil;
import com.shopping.conmonUtil.cont.Constant;
import com.shopping.conmonUtil.exception.ConfMissingException;
import com.shopping.conmonUtil.model.EsPage;
import com.shopping.conmonUtil.model.EsQueryCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

/**
 * es工具类
 * @author buxunxiang
 *
 */
public class EsUtil {

	private static final Log LOG = LogFactory.getLog(EsUtil.class);
	//private static Properties prop =  new  Properties();   
	private static TransportClient client;
	
	static 
	{
        //prop =  PropertiesUtil.loadProperties("elasticsearch.properties");
        
        try {
			buildTransportClient();
		} catch (ConfMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 建立es的client
	 * 
	 * @param conf
	 *            配置信息
	 * @return 返回client
	 * @throws ConfMissingException
	 *             抛出异常
	 * @throws java.net.UnknownHostException
	 */
    public static void buildTransportClient() throws ConfMissingException{

        if ((PropertiesInfoUtil.getElasticSearchInfo().getProperty("discovery.zen.ping.unicast.hosts") == null) || (PropertiesInfoUtil.getElasticSearchInfo().getProperty("cluster.name") == null)) {
            throw new ConfMissingException("Conf missing for elasticsearch, check yourself es'config file");
        }

        // 获取当前的集群名字
        Settings settings = Settings.settingsBuilder().put("cluster.name", PropertiesInfoUtil.getElasticSearchInfo().getProperty("cluster.name")).build();
        client = TransportClient.builder().settings(settings).build();
        // 可选的集群master
        String[] hosts = PropertiesInfoUtil.getElasticSearchInfo().getProperty("discovery.zen.ping.unicast.hosts").toString().split(",");
        for (String host : hosts) {
            try {
                String portString = PropertiesInfoUtil.getElasticSearchInfo().getProperty("portnumber");
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Integer.parseInt(portString)));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 部门：软件开发事业部
     * 功能：创建索引库；定义索引的映射类型
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018-3-29
     */
    public static void createMapping(String indices, String mappingType) throws IOException{
        // 创建空的索引库
        client.admin().indices().prepareCreate(indices).execute().actionGet();

        // put mapping
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("ID").field("type", "string").field("index", "not_analyzed").endObject()// not_analyzed 不分词 合约商品id
                .startObject("CONTRACT_GOODS_ID").field("type", "string").field("index", "not_analyzed").endObject()// not_analyzed 不分词 合约商品id
                .startObject("CONTRACT_GOODS_CODE").field("type", "string").field("store", "yes").field("analyzer", "ik_syno").field("search_analyzer", "ik_max_word").endObject()// 合约商品编码
                .startObject("CONTRACT_GOODS_NAME").field("type", "string").field("store", "yes").field("analyzer", "ik_syno").field("search_analyzer", "ik_max_word").endObject()// 合约商品名称
                .startObject("CONTRACTED").field("type", "short").field("store", "yes").endObject()// 是否合约
                .startObject("MIN_PACKAGE").field("type", "integer").field("store", "yes").endObject()// 最小订购单位
                .startObject("EVALUATE").field("type", "double").field("store", "yes").endObject()// 合约商品评分
                .startObject("SALES_ACCOUNT").field("type", "long").field("store", "yes").endObject()// 订货总量
                .startObject("PRICE").field("type", "double").field("store", "yes").endObject()// 价格（通过是否合约处理过）
                .startObject("GOODS_ID").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()// not_analyzed 不分词 商品id
                .startObject("GOODS_CODE").field("type", "string").field("store", "yes").field("analyzer", "ik_syno").field("search_analyzer", "ik_max_word").endObject()// 商品code
                .startObject("GOODS_PIC").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()//商品首图
                .startObject("GOOODS_DESC").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()//商品描述
                .startObject("GOODS_NAME").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()//商品描述
                .startObject("KEY_WORDS").field("type", "string").field("store", "yes").field("analyzer", "ik_syno").field("search_analyzer", "ik_max_word").endObject()// 商品key_words
                .startObject("PRICE_A").field("type", "double").field("store", "yes").endObject()// A价格
                .startObject("PRICE_B").field("type", "double").field("store", "yes").endObject()// B价格
                .startObject("PRICE_C").field("type", "double").field("store", "yes").endObject()// C价格
                .startObject("IS_LACK").field("type", "short").field("store", "yes").endObject()// 是否缺货
                .startObject("CATGORY_ID").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()// not_analyzed 不分词 分类id
                .startObject("CATEGORY_CODE").field("type", "string").field("store", "yes").field("index", "not_analyzed").endObject()// not_analyzed 不分词 分类id
                .startObject("CATEGORY_TREE_CODE").field("type", "string").field("store", "yes").field("analyzer", "ik_syno").field("search_analyzer", "ik_max_word").endObject()// 树状查询码
                .startObject("CONTRACT_CUSTOMER_ID").field("type", "string").field("store", "yes").endObject()// 合约客户id
                .startObject("SUPPLIER_ID").field("type", "string").field("store", "yes").endObject()// 供应商id
                .startObject("SUPPLIER_NAME").field("type", "string").field("store", "yes").endObject()// 供应商名称
                .startObject("BRAND_ID").field("type", "string").field("store", "yes").endObject()// 品牌id
                .startObject("BRAND_NAME").field("type", "string").field("store", "yes").endObject()// 品牌名称
                .endObject().endObject();

        PutMappingRequest mapping = Requests.putMappingRequest(indices)
                .type(mappingType).source(builder);

        client.admin().indices().putMapping(mapping).actionGet();
//				client.close();
    }

    public static void main(String[] args) throws ConfMissingException, IOException {
        buildTransportClient();

        createMapping("zjmi-wc-test", "goods");// index mappingType

//		client.close();
    }
	
	/**
	 * 部门：软件开发事业部
	 * 功能：通过prepareIndex增加文档，参数为json字符串 
	 * 描述：index 索引名/type  类型 /_id   数据id /json  数据 
	 * 作成者：朱庆锋
	 * 作成时间：2018-3-30
	 */
	public static void insertData2ES(String index, String type, String _id, Object obj) {  
        IndexResponse indexResponse = client.prepareIndex(index, type).setId(_id)  
                .setSource(JsonUtils.parseObject2JSON(obj))
                .get();  
        System.out.println(indexResponse.getVersion());  
        LOG.info("数据插入ES成功！");
//		client.close();  
	}
	
	/**
	 * 部门：软件开发事业部
	 * 功能：批量导入数据
	 * 描述：index 索引名/type 类型 /list  数据 
	 * 作成者：朱庆锋
	 * 作成时间：2018-3-30
	 */
	public static void importData2ES(String index, String type, List<Map<String, Object>> list) { 
		if (null == list || 0 == list.size()) {
			LOG.warn("当前所传的数据信息为空，不能创建索引！");
			return;
		}
		
		BulkRequestBuilder builder = client.prepareBulk();// 批量索引，提高索引速度
		
		for(int i = 0 ; i < list.size(); i++){
			
			builder.add(client.prepareIndex(index, type, String.valueOf(list.get(i).get("ID")))
					.setSource(JsonUtils.parseObject2JSON(list.get(i))));
		}
		
		//提交请求
		BulkResponse response = builder.execute().actionGet();
		
		if(response.hasFailures()){
			BulkItemResponse[] responses = response.getItems();
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < responses.length; i++) {
				if (responses[i].isFailed()) {
					BulkItemResponse.Failure failure = responses[i].getFailure();
					sb.append("fail index : ").append(failure.getIndex()).append("\ndetail fail message : ")
							.append(failure.getMessage()).append("\n");
				}
			}
			
			LOG.error(sb.toString());
		}
//		client.close();
	}
	
	/**
	 * 部门：软件开发事业部
	 * 功能：更新数据 
	 * 描述：index 索引名/type 类型 /list  数据 
	 * 作成者：朱庆锋
	 * 作成时间：2018-3-30
	 */
    public static void updateData(String index, String type, String _id, Object obj){  
        try {
        	//UpdateResponse response = client.prepareUpdate(index, type, _id).get();
        	if(existsById(index, type, _id)){
        		UpdateRequest updateRequest = new UpdateRequest(index, type, _id)
        			.doc(JsonUtils.parseObject2JSON(obj));  
//              client.prepareUpdate(index, type, _id).setDoc(json).get();  
        		client.update(updateRequest).get();  
//        		client.close();
        	} else {
        		insertData2ES(index, type, _id, obj);
        	}
            
        } catch (Exception e) {  
        	LOG.error("update data failed." + e.getMessage());  
        }  
    }
    
    /**
     * 判断数据是否存在
     * @param id
     * @return
     */
    public static boolean existsById(String index, String type, String id){
    	 SearchRequestBuilder searchBuilder = client.prepareSearch(index).setTypes(type)
				.setQuery(new TermQueryBuilder("id", id))//设置查询类型
				.setSearchType(SearchType.COUNT)//设置查询类型，有的版本可能过期
				;
		SearchResponse response = searchBuilder.get();
    	long length = response.getHits().totalHits();
    	return length > 0;
    }
    
    /**
	 * 部门：软件开发事业部
	 * 功能：批量更新数据 
	 * 描述：index 索引名/type 类型 /list  数据 
	 * 作成者：朱庆锋
	 * 作成时间：2018-3-30
	 */
    public static void bulkUpdate(String index, String type, List<Map<String, Object>> list){
    	try {
    		for (int i = 0; i < list.size(); i++) {
    			String _id = String.valueOf(list.get(i).get("ID"));
//    			UpdateResponse response = client.prepareUpdate(index, type, _id).get();
            	if(existsById(index, type, _id)){
            		UpdateRequest updateRequest = new UpdateRequest(index, type, _id)
            			.doc(JsonUtils.parseObject2JSON(list.get(i)));  
//                  client.prepareUpdate(index, type, _id).setDoc(json).get();  
            		client.update(updateRequest).get();  
            	} else {
            		IndexResponse indexResponse = client.prepareIndex(index, type).setId(_id)  
                            .setSource(JsonUtils.parseObject2JSON(list.get(i)))
                            .get();  
            	}
                
			}
//    		client.close();
    		
        } catch (Exception e) {  
        	LOG.error("update data failed." + e.getMessage());  
        }  
    }
    
    /**
     * 部门：软件开发事业部
     * 功能：删除数据
     * 描述：index 索引名/type  类型 /_id   数据id
     * 作成者：朱庆锋
     * 作成时间：2018-4-2
     */
    public static void deleteDocument(String index, String type, String _id){
		DeleteResponse response = client.prepareDelete(index, type, _id)
		        .get();
		
		//找到要删除的document
		if(response.isFound()){
			client.delete(new DeleteRequest(index, type, _id)).actionGet();//参数为：index  type  ID
		} else {
			LOG.error("没有找到document");
		}
//		client.close();
	}
	
	/**
	 * 部门：软件开发事业部
	 * 功能：根据商品搜索
	 * 描述：略
	 * 作成者：朱庆锋
	 * 作成时间：2018-4-2
	 */
	public static SearchResponse searchByGoods(EsPage page) {
        EsPage page1 = page;
		SearchRequestBuilder requestBuilder = client.prepareSearch(page.getIndex())
				.setTypes(page.getType());

		List<EsQueryCondition> conditionList = page.getCondition();
		if (null != conditionList && !conditionList.isEmpty()) {
			// 初始化查询所有
			//BoolQueryBuilder booleanQuery = boolQuery().must(matchAllQuery());
			BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
			for (EsQueryCondition condition : conditionList) {
				
				if (Constant.OPER_AND.endsWith(condition.getOperation())) {
					
					if (Constant.QUERY_PREFIX.equals(condition.getQueryType())) {// 前缀匹配
						booleanQuery.must(QueryBuilders.prefixQuery(condition.getFieldName(), condition.getValue()));
					} else if (Constant.QUERY_EQUAL.equals(condition.getQueryType())) {// 
						//booleanQuery.must(new TermQueryBuilder(condition.getFieldName(), condition.getValue()));
						
						if("SEARCH_CONTENT".equals(condition.getFieldName())){
								//9.fuzzy_like_this，fuzzy_like_this_field，fuzzy查询
				                //fuzzyQuery:使用模糊查询匹配文档查询
							booleanQuery.must(QueryBuilders.queryStringQuery(condition.getValue()));
								//booleanQuery.must(QueryBuilders.termQuery("_all", condition.getValue())
										/*QueryBuilders.boolQuery()
						                  .should(QueryBuilders.fuzzyQuery("CONTRACT_GOODS_NAME", condition.getValue()))
						                  .should(QueryBuilders.fuzzyQuery("CONTRACT_GOODS_CODE", condition.getValue()))
						                  .should(QueryBuilders.fuzzyQuery("GOODS_CODE", condition.getValue()))
						                  .should(QueryBuilders.fuzzyQuery("KEY_WORDS", condition.getValue()))*/
						} else {
							//booleanQuery.must(QueryBuilders.matchQuery(condition.getFieldName(), condition.getValue()));
							//booleanQuery.must(QueryBuilders.termQuery("_all", condition.getValue()));
							//q = QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(condition.getValue()));
							//booleanQuery.must(QueryBuilders.termQuery(condition.getFieldName(), condition.getValue()));
							booleanQuery.must(QueryBuilders.termQuery(condition.getFieldName(), condition.getValue()));
						}
					}
						
				} else {
					booleanQuery.should(QueryBuilders.wildcardQuery(condition.getFieldName(), condition.getValue()));
				}
			}

			if (null != booleanQuery) {
				requestBuilder.setQuery(booleanQuery);
			}
			
		}

		// 获取当前排序字段
		Map<String, String> sortMap = page.getSortMap();
		if (null != sortMap && 0 < sortMap.size()) {
			for (String key : sortMap.keySet()) {
				if (Constant.DESC.equals(sortMap.get(key))) {
					SortOrder order = SortOrder.DESC;
					FieldSortBuilder fieldSort = new FieldSortBuilder(key).order(order).ignoreUnmapped(true);
					requestBuilder.addSort(fieldSort);
				} else if (Constant.ASC.equals(sortMap.get(key))) {
					SortOrder order = SortOrder.ASC;
					FieldSortBuilder fieldSort = new FieldSortBuilder(key).order(order).ignoreUnmapped(true);
					requestBuilder.addSort(fieldSort);
				}
			}
		}

		// 分页查询
		requestBuilder.setFrom(page.getPAGE_INDEX()).setSize(page.getPAGE_SIZE())
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		long begin = System.currentTimeMillis();
		SearchResponse response = (SearchResponse) requestBuilder.execute().actionGet();
		
		if (response.getFailedShards() != 0) {
			LOG.warn("has failed shards, failed number : " + response.getFailedShards());
			for (ShardSearchFailure failure : response.getShardFailures()) {
				StringBuffer sb = new StringBuffer();
				sb.append("shard : ").append(failure.shard()).append(",\n");
				sb.append("status : ").append(failure.status()).append(",\n");
				sb.append("reason : ").append(failure.reason()).append(".\n");
				LOG.warn(sb.toString());
			}
		}

		SearchHit[] hints = response.getHits().getHits();
		long end = System.currentTimeMillis();
		System.out.println("---------------查询时间=" + (end- begin));
        System.out.println(hints.length);
		//client.close();
		return response;
	}
	
	/**
	 * 部门：软件开发事业部
	 * 功能：根据品牌搜索
	 * 描述：略
	 * 作成者：朱庆锋
	 * 作成时间：2018-4-2
	 */
	public static SearchResponse searchByBrand(EsPage page) {
		SearchRequestBuilder requestBuilder = client.prepareSearch(page.getIndex())
				.setTypes(page.getType());

		List<EsQueryCondition> conditionList = page.getCondition();
		if (null != conditionList && !conditionList.isEmpty()) {
			// 初始化查询所有
			//BoolQueryBuilder booleanQuery = boolQuery().must(matchAllQuery());
			BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
			for (EsQueryCondition condition : conditionList) {
				if (Constant.OPER_AND.endsWith(condition.getOperation())) {
					
					if (Constant.QUERY_PREFIX.equals(condition.getQueryType())) {// 前缀匹配
						booleanQuery.must(QueryBuilders.prefixQuery(condition.getFieldName(), condition.getValue()));
					} else if (Constant.QUERY_EQUAL.equals(condition.getQueryType())) {
						//booleanQuery.must(new TermQueryBuilder(condition.getFieldName(), condition.getValue()));
						
						if("SEARCH_CONTENT".equals(condition.getFieldName())){
							// 按品牌查
							//booleanQuery.must(QueryBuilders.matchQuery("BRAND_NAME", condition.getValue()));
							booleanQuery.must(QueryBuilders.queryStringQuery(condition.getValue()));
						} else {
							//booleanQuery.must(QueryBuilders.matchQuery(condition.getFieldName(), condition.getValue()));
							booleanQuery.must(QueryBuilders.termQuery(condition.getFieldName(), condition.getValue()));
						}
					}
						
				} else {
					booleanQuery.should(QueryBuilders.wildcardQuery(condition.getFieldName(), condition.getValue()));
				}
			}

			if (null != booleanQuery) {
				requestBuilder.setQuery(booleanQuery);
			}

		}

		// 获取当前排序字段
		Map<String, String> sortMap = page.getSortMap();
		if (null != sortMap && 0 < sortMap.size()) {
			for (String key : sortMap.keySet()) {
				if (Constant.DESC.equals(sortMap.get(key))) {
					SortOrder order = SortOrder.DESC;
					FieldSortBuilder fieldSort = new FieldSortBuilder(key).order(order).ignoreUnmapped(true);
					requestBuilder.addSort(fieldSort);
				} else if (Constant.ASC.equals(sortMap.get(key))) {
					SortOrder order = SortOrder.ASC;
					FieldSortBuilder fieldSort = new FieldSortBuilder(key).order(order).ignoreUnmapped(true);
					requestBuilder.addSort(fieldSort);
				}
			}
		}

		// 分页查询
		requestBuilder.setFrom(page.getPAGE_INDEX()).setSize(page.getPAGE_SIZE())
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		long begin = System.currentTimeMillis();
		SearchResponse response = (SearchResponse) requestBuilder.execute().actionGet();
		
		if (response.getFailedShards() != 0) {
			LOG.warn("has failed shards, failed number : " + response.getFailedShards());
			for (ShardSearchFailure failure : response.getShardFailures()) {
				StringBuffer sb = new StringBuffer();
				sb.append("shard : ").append(failure.shard()).append(",\n");
				sb.append("status : ").append(failure.status()).append(",\n");
				sb.append("reason : ").append(failure.reason()).append(".\n");
				LOG.warn(sb.toString());
			}
		}

		SearchHit[] hints = response.getHits().getHits();
		long end = System.currentTimeMillis();
		System.out.println(end- begin);
        System.out.println(hints.length);
		//client.close();
		return response;
	}

}
