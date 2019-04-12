package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangas on 2018/7/23.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public APIResponse exception(HttpServletRequest request, Exception e){

        logger.error(e.getMessage());

        return null;
    }

}
