package com.shopping.configuration;

import cn.zjmiec.xks.core.Constants;
import com.shopping.filter.SessionFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by zhangas on 2018/7/29.
 */
@ConditionalOnExpression("#{'${session.manager.pattern:}'.trim().length() > 0}")
@Configuration
public class SessionConfiguration {

    @Value("${session.manager.pattern:}")
    private String sessionManagerPattern;

    @Bean
    public FilterRegistrationBean sessionFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        String[] pattern = null;
        if(!StringUtils.isEmpty(sessionManagerPattern)){
            pattern = sessionManagerPattern.split(",");
        }
        registration.addUrlPatterns(pattern);
        registration.setName("sessionFilter");
        registration.setOrder(Constants.FilterOrderGroups.SESSION_FILTER + 1);
        return registration;
    }

    @Bean
    public Filter sessionFilter(){
        return new SessionFilter();
    }
}
