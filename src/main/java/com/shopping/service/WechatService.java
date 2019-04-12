package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.SessionManagerJsonBean;


/**
 * Created by zhangas on 2018/7/30.
 */
public interface WechatService {

    SessionManagerJsonBean validataAutoLogin(String openId);

    APIResponse bind(String sourceOpenId, String currentOpenId);

}
