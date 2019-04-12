package com.shopping.filter;

import cn.zjmiec.xks.core.mvc.APIResponse;
import cn.zjmiec.xks.redis.DefaultRedisService;
import com.alibaba.fastjson.JSON;
import com.shopping.common.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangas on 2018/7/29.
 */
public class SessionFilter implements Filter{

    private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String token = request.getParameter("token");
        if(!StringUtils.isEmpty(token)){
            DefaultRedisService defaultRedisService = SpringUtils.getBean(DefaultRedisService.class);
            if(!defaultRedisService.exists(token)){
                writeResponse(request,response);
            }

            defaultRedisService.expire(token, Integer.valueOf( System.getProperty("session.timeout")));
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            writeResponse(request, response);
        }
    }

    private void writeResponse(HttpServletRequest request,HttpServletResponse response)throws IOException{
        PrintWriter out = response.getWriter();
        APIResponse apiResponse = APIResponse.fail("session time out");
        apiResponse.setCode("1000");
        out.print(JSON.toJSONString(apiResponse));
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {

    }
}
