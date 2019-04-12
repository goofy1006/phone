package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import cn.zjmiec.xks.redis.DefaultRedisService;
import com.shopping.dao.EmWcBindMapperExt;
import com.shopping.dao.PhoneUserMapperExt;
import com.shopping.model.EmWcBind;
import com.shopping.model.PhoneUser;
import com.shopping.model.jsonbean.SessionManagerJsonBean;
import com.shopping.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhangas on 2018/7/30.
 */
@Service
public class WechatServiceImpl implements WechatService {

    private static Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private PhoneUserMapperExt phoneUserMapperExt;

    @Autowired
    private DefaultRedisService defaultRedisService;

    @Autowired
    private EmWcBindMapperExt emWcBindMapperExt;

    @Override
    public SessionManagerJsonBean validataAutoLogin(String openId) {
        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setOpenId(openId);
        List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(phoneUser);
        SessionManagerJsonBean sessionManagerJsonBean = new SessionManagerJsonBean();
        if(CollectionUtils.isEmpty(phoneUserList)){
            sessionManagerJsonBean.setOpenId(openId);
        }else{
            PhoneUser indexPhoneUser = phoneUserList.get(0);
            sessionManagerJsonBean.setOpenId(openId);
            sessionManagerJsonBean.setUserName(indexPhoneUser.getUserName());
            sessionManagerJsonBean.setTel(indexPhoneUser.getTel());
            sessionManagerJsonBean.setCustomerId(indexPhoneUser.getUuid());
            String token = CommonUtil.getUUID();
            sessionManagerJsonBean.setToken(token);
            defaultRedisService.set(token, sessionManagerJsonBean, 30 * 60);
        }
        return sessionManagerJsonBean;
    }

    @Override
    public APIResponse bind(String sourceOpenId, String currentOpenId) {
        EmWcBind wcBind = new EmWcBind();
        wcBind.setCurrentOpenid(currentOpenId);
        List<EmWcBind> emWcBindList = emWcBindMapperExt.selectList(wcBind);
        if(!CollectionUtils.isEmpty(emWcBindList)){
            logger.error("currentOpenId:"+currentOpenId+"已被邀请过！");
            return APIResponse.success("当前用户已被邀请过！");
        }
        wcBind.setUuid(CommonUtil.getUUID());
        wcBind.setSourceOpenid(sourceOpenId);
        emWcBindMapperExt.insertSelective(wcBind);
        return APIResponse.success("绑定成功！");
    }
}
