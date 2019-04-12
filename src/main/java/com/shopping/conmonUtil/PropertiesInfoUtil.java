package com.shopping.conmonUtil;

import java.util.Properties;

public class PropertiesInfoUtil {
	static Properties elasticSearchProperties = null;
	static {
		elasticSearchProperties = PropertiesUtil.loadProperties("elasticsearch.properties");
	}
	public static Properties getElasticSearchInfo(){
		return elasticSearchProperties;
	}
}
