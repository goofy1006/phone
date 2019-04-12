package com.shopping.conmonUtil.model;

import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;
import java.util.Map;

public class EsPage {

	// 开始位置
	int PAGE_INDEX;
	
	// 批量查询的个数,默认查询出10条数据
	int PAGE_SIZE;
	
	// 需要查询的个数
	int totalSize;
	
	// 查询器
	QueryBuilder queryBuilder;
	
	// 键位字段名， 值为升序还是降序
	Map<String, String> sortMap;
	
	// index名字
	String index;
	
	// 类型
	String type;
	
	//  查询条件
	List<EsQueryCondition> condition;
	
	public int getPAGE_INDEX() {
		return PAGE_INDEX;
	}
	public void setPAGE_INDEX(int pAGE_INDEX) {
		PAGE_INDEX = pAGE_INDEX;
	}
	public int getPAGE_SIZE() {
		if (0 == PAGE_SIZE){
			 PAGE_SIZE = 10;
	    }
		return PAGE_SIZE;
	}
	public void setPAGE_SIZE(int pAGE_SIZE) {
		PAGE_SIZE = pAGE_SIZE;
	}
	public QueryBuilder getQueryBuilder() {
		return queryBuilder;
	}
	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}
	
	public Map<String, String> getSortMap() {
		return sortMap;
	}
	public void setSortMap(Map<String, String> sortMap) {
		this.sortMap = sortMap;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<EsQueryCondition> getCondition() {
		return condition;
	}
	public void setCondition(List<EsQueryCondition> condition) {
		this.condition = condition;
	}
	
	
	
}
