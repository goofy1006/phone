package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import cn.zjmiec.xks.redis.DefaultRedisService;
import com.alibaba.fastjson.JSON;
import com.shopping.common.Constants;
import com.shopping.common.SendSmsUtil;
import com.shopping.dao.EmTiTicketMapperExt;
import com.shopping.dao.EmTiUserTicketMapperExt;
import com.shopping.dao.EmWcBindMapperExt;
import com.shopping.dao.PhoneUserMapperExt;
import com.shopping.model.*;
import com.shopping.model.jsonbean.SessionManagerJsonBean;
import com.shopping.service.IEmCmMessageService;
import com.shopping.service.PhoneUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by zhangas on 2018/7/23.
 */
@Service
public class PhoneUserServiceImpl implements PhoneUserService {

    private static Logger logger = LoggerFactory.getLogger(PhoneUserServiceImpl.class);

    //短信模板ID
    @Value("${templateCode}")
    private String templateCode;
    //接口调用系统名
    @Value("${sysId}")
    private String sysId;
    //短信签名
    @Value("${signName}")
    private String signName;

    @Value("${session.timeout:}")
    private Integer timeout;

    @Autowired
    private PhoneUserMapperExt phoneUserMapperExt;

    @Autowired
    private DefaultRedisService defaultRedisService;

    @Autowired
    private EmTiTicketMapperExt emTiTicketMapperExt;

    @Autowired
    private EmTiUserTicketMapperExt emTiUserTicketMapperExt;

    @Autowired
    private EmWcBindMapperExt wcBindMapperExt;
    @Autowired
    private IEmCmMessageService iEmCmMessageService;

    @Override
    public APIResponse validataUserName(String userName) {

        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setUserName(userName);
        List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(phoneUser);
        if(phoneUserList.size() > 0){
            return APIResponse.fail("用户名已存在！");
        }
        return APIResponse.success("用户名不存在！");
    }

    @Override
    public APIResponse validataTel(String tel) {
        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setTel(tel);
        List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(phoneUser);
        if(phoneUserList.size() > 0){
            return APIResponse.fail("手机号已存在！");
        }
        return APIResponse.success("手机号不存在！");
    }

    @Override
    public APIResponse smsCode(String tel) {
        int code = (int)((Math.random()*9+1)*1000);
        Map<String,String> templateParam = new HashMap<>();
        templateParam.put("code",code+"");
        try {
            SendSmsUtil.sendSms(tel,templateCode,templateParam,sysId,tel,signName,0);
        }catch (Exception e){
            logger.info(e.getMessage());
            return APIResponse.fail("短信发送失败，请重试");
        }
        defaultRedisService.set(tel, code, 60);
        //生成4位随机数返回
        return APIResponse.success(code);
    }

    @Transactional
    @Override
    public APIResponse register(String userName, String password, String tel, String code, String openId) {
        //缓存验证验证码是否通过
        if(!defaultRedisService.exists(tel)){
            return APIResponse.fail("验证码失效！");
        }
        String redisCode = defaultRedisService.get(tel);
        if(!code.equals(redisCode)){
            return APIResponse.fail("验证码错误！");
        }

        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setUserName(userName);
        List<PhoneUser> indexPhoneUserList = phoneUserMapperExt.selectList(phoneUser);
        if(indexPhoneUserList != null && indexPhoneUserList.size() > 0){
            return APIResponse.fail("重复注册！");
        }
        String userId = CommonUtil.getUUID();
        phoneUser.setUuid(userId);
        phoneUser.setPassword(CommonUtil.MD5(password));
        phoneUser.setTel(tel);
        phoneUser.setOpenId(openId);
        int result = phoneUserMapperExt.insertSelective(phoneUser);
        try {
            iEmCmMessageService.insertEmCmMessage(userId,"注册成功消息","恭喜您：尊敬的"+userName+"用户，注册成功");
        }catch (Exception e){
            logger.error("注册成功消息插入失败"+e.getMessage());
        }
        EmTiTicket emTiTicket = new EmTiTicket();
        emTiTicket.setIssueWay(Constants.IssueWay.AUTO_RECEIPT);
        emTiTicket.setIsIssue(Constants.IsIssue.ALREADY_ISSUED);
        List<EmTiTicket> emTiTicketList = emTiTicketMapperExt.selectList(emTiTicket);
        if(CollectionUtils.isNotEmpty(emTiTicketList)){
            //区分券类型
           List<EmTiTicket> newUserTickets = new ArrayList<>();
           List<EmTiTicket> wechatShareTickets = new ArrayList<>();
            for(EmTiTicket ticket : emTiTicketList){
                if(ticket.getIssueTotal() > ticket.getDrawCount()){
                    if(ticket.getSendUser() == Constants.SendUser.NEW_USER){
                        newUserTickets.add(ticket);
                    }else if(ticket.getSendUser() == Constants.SendUser.WECHAT_SHARE_USER){
                        wechatShareTickets.add(ticket);
                    }
                }
            }
            //新用户券
            if(CollectionUtils.isNotEmpty(newUserTickets)){
                for(EmTiTicket ticket : newUserTickets){
                    EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
                    emTiUserTicket.setId(CommonUtil.getUUID());
                    emTiUserTicket.setTicketId(ticket.getTicketId());
                    emTiUserTicket.setUserId(userId);
                    Integer validType = ticket.getValidType();
                    if(validType == Constants.ValidType.FIXED_VALUE){
                        emTiUserTicket.setEfstartTime(ticket.getEfstartTime());
                        emTiUserTicket.setEfendTime(ticket.getEfendTime());
                    }else{
                        Date efstartTime = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(efstartTime);
                        calendar.add(Calendar.DAY_OF_MONTH, ticket.getValidVal());
                        Date efendTime = calendar.getTime();
                        emTiUserTicket.setEfstartTime(efstartTime);
                        emTiUserTicket.setEfendTime(efendTime);
                    }
                    emTiUserTicket.setIsUse(Constants.TicketIsUse.NOT_USE);
                    emTiUserTicket.setCreatedBy(userName);
                    emTiUserTicketMapperExt.insertSelective(emTiUserTicket);

                    ticket.setDrawCount(ticket.getDrawCount() + 1);
                    emTiTicketMapperExt.updateByPrimaryKeySelective(ticket);
                }
            }
            //查询该用户是否是被邀请
            EmWcBind emWcBind = new EmWcBind();
            emWcBind.setCurrentOpenid(openId);
            List<EmWcBind> wcBindList = wcBindMapperExt.selectList(emWcBind);
            //是被邀请人员
            if(CollectionUtils.isNotEmpty(wcBindList)){
                EmWcBind bind = wcBindList.get(0);
                //邀请人OpenId
                String sourceOpenId = bind.getSourceOpenid();
                //微信分享券
                if(CollectionUtils.isNotEmpty(wechatShareTickets)){
                    //微信分享券只赠送一种 邀请人和被邀请人同时拥有
                    EmTiTicket ticket = wechatShareTickets.get(0);
                    //需要赠送用户的主键
                    List<String> userIdsList = new ArrayList<>();
                    //查询邀请人信息
                    PhoneUser user = new PhoneUser();
                    user.setOpenId(sourceOpenId);
                    List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(user);
                    if(CollectionUtils.isNotEmpty(phoneUserList)){
                        userIdsList.add(phoneUserList.get(0).getUuid());
                    }
                    if(ticket.getIssueTotal() - ticket.getDrawCount() > 1){
                        userIdsList.add(userId);
                    }
                   for(String indexUserId : userIdsList){
                       EmTiUserTicket emTiUserTicket = new EmTiUserTicket();
                       emTiUserTicket.setId(CommonUtil.getUUID());
                       emTiUserTicket.setTicketId(ticket.getTicketId());
                       emTiUserTicket.setUserId(indexUserId);
                       Integer validType = ticket.getValidType();
                       if(validType == Constants.ValidType.FIXED_VALUE){
                           emTiUserTicket.setEfstartTime(ticket.getEfstartTime());
                           emTiUserTicket.setEfendTime(ticket.getEfendTime());
                       }else{
                           Date efstartTime = new Date();
                           Calendar calendar = Calendar.getInstance();
                           calendar.setTime(efstartTime);
                           calendar.add(Calendar.DAY_OF_MONTH, ticket.getValidVal());
                           Date efendTime = calendar.getTime();
                           emTiUserTicket.setEfstartTime(efstartTime);
                           emTiUserTicket.setEfendTime(efendTime);
                       }
                       emTiUserTicket.setIsUse(Constants.TicketIsUse.NOT_USE);
                       emTiUserTicket.setCreatedBy(userName);
                       emTiUserTicketMapperExt.insertSelective(emTiUserTicket);
                   }
                    ticket.setDrawCount(ticket.getDrawCount() + userIdsList.size());
                    emTiTicketMapperExt.updateByPrimaryKeySelective(ticket);
                }
            }
        }
        if(result > 0){
            SessionManagerJsonBean sessionManagerJsonBean = new SessionManagerJsonBean();
            sessionManagerJsonBean.setToken(CommonUtil.getUUID());
            sessionManagerJsonBean.setCustomerId(phoneUser.getUuid());
            sessionManagerJsonBean.setTel(phoneUser.getTel());
            sessionManagerJsonBean.setUserName(phoneUser.getUserName());
            sessionManagerJsonBean.setOpenId(phoneUser.getOpenId());
            defaultRedisService.set(sessionManagerJsonBean.getToken(), sessionManagerJsonBean, timeout);
            return APIResponse.success(sessionManagerJsonBean);
        }else{
            logger.error("register fail ：" + JSON.toJSONString(phoneUser));
            return APIResponse.fail("注册失败！");
        }
    }

    @Override
    public APIResponse smsLogin(HttpServletRequest request, String tel, String code, String openId) {
        //缓存验证验证码是否通过
        if(!defaultRedisService.exists(tel)){
            return APIResponse.fail("验证码失效！");
        }
        String redisCode = defaultRedisService.get(tel);
        if(!code.equals(redisCode)){
            return APIResponse.fail("验证码错误！");
        }

        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setTel(tel);
        List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(phoneUser);
        if(CollectionUtils.isEmpty(phoneUserList)){
            return APIResponse.fail("不存在该用户！");
        }
        PhoneUser indexPhoneUser = phoneUserList.get(0);

        SessionManagerJsonBean sessionManagerJsonBean = new SessionManagerJsonBean();
        sessionManagerJsonBean.setToken(CommonUtil.getUUID());
        sessionManagerJsonBean.setCustomerId(indexPhoneUser.getUuid());
        sessionManagerJsonBean.setTel(indexPhoneUser.getTel());
        sessionManagerJsonBean.setUserName(indexPhoneUser.getUserName());
        sessionManagerJsonBean.setOpenId(indexPhoneUser.getOpenId());
        defaultRedisService.set(sessionManagerJsonBean.getToken(), sessionManagerJsonBean, timeout);
        if(StringUtils.isNotEmpty(openId)){
            indexPhoneUser.setOpenId(openId);
            phoneUserMapperExt.updateByPrimaryKeySelective(indexPhoneUser);
        }
        try {
            iEmCmMessageService.insertEmCmMessage(indexPhoneUser.getUuid(),"登陆成功消息","欢迎您：尊敬的"+indexPhoneUser.getUserName());
        }catch (Exception e){
            logger.error("登陆成功消息插入失败"+e.getMessage());
        }
        return APIResponse.success(sessionManagerJsonBean);
    }

    @Override
    public APIResponse login(HttpServletRequest request, String userName, String password, String openId) {
        PhoneUser phoneUser = new PhoneUser();
        phoneUser.setUserName(userName);
        List<PhoneUser> phoneUserList = phoneUserMapperExt.selectList(phoneUser);
        if(CollectionUtils.isEmpty(phoneUserList)){
            return APIResponse.fail("不存在该用户！");
        }
        PhoneUser indexPhoneUser = phoneUserList.get(0);
        if(!indexPhoneUser.getPassword().equals(CommonUtil.MD5(password))){
            return APIResponse.fail("密码错误！");
        }

        SessionManagerJsonBean sessionManagerJsonBean = new SessionManagerJsonBean();
        sessionManagerJsonBean.setToken(CommonUtil.getUUID());
        sessionManagerJsonBean.setCustomerId(indexPhoneUser.getUuid());
        sessionManagerJsonBean.setTel(indexPhoneUser.getTel());
        sessionManagerJsonBean.setUserName(indexPhoneUser.getUserName());
        sessionManagerJsonBean.setOpenId(indexPhoneUser.getOpenId());
        defaultRedisService.set(sessionManagerJsonBean.getToken(), sessionManagerJsonBean, timeout);
        if(StringUtils.isNotEmpty(openId)){
            indexPhoneUser.setOpenId(openId);
            phoneUserMapperExt.updateByPrimaryKeySelective(indexPhoneUser);
        }
        try {
            iEmCmMessageService.insertEmCmMessage(indexPhoneUser.getUuid(),"登陆成功消息","欢迎您：尊敬的"+indexPhoneUser.getUserName());
        }catch (Exception e){
            logger.error("登陆成功消息插入失败"+e.getMessage());
        }
        return APIResponse.success(sessionManagerJsonBean);
    }
}
