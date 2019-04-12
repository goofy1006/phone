package com.shopping.conmonUtil.model;


import com.shopping.conmonUtil.cont.Constant;

public class EsQueryCondition {

	// 字段名字
	private String fieldName;
	
	// 查询类型 like 还是等值
	private String queryType;
	
	// 字段值
	private String value;
	
	// 范围值，range的to值
	private String secondValue;
	
	// 操作符 and or
	private String operation;

	
	public EsQueryCondition()
	{
		
	}
	
	public EsQueryCondition(String fieldName, String queryType, String value)
	{
		this.fieldName = fieldName;
		this.queryType = queryType;
		this.value = value;
	}
	
	public EsQueryCondition(String fieldName, String queryType, String value, String operation)
	{
		this.fieldName = fieldName;
		this.queryType = queryType;
		this.value = value;
		this.operation = operation;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperation() {
		if (null == operation || "".equals(operation))
		{
			return Constant.OPER_AND;
		}
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}
	
	
}
