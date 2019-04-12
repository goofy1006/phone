package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.dao.PhoneUserMapperExt;
import com.shopping.model.PhoneUser;
import com.shopping.service.PhoneUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangas on 2018/7/23.
 */
@Api(tags = "手机登录、注册等操作",value = "", description = "")
@RestController
@RequestMapping("/phone/")
public class PhoneUserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(PhoneUserController.class);

    @Autowired
    private PhoneUserService phoneUserService;


    @ApiOperation(value = "验证用户名是否存在", notes = "")
    @RequestMapping(value = "validataUserName.do", method = RequestMethod.POST)
    public APIResponse validataUserName(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam String userName
    ){
        return phoneUserService.validataUserName(userName);
    }

    @ApiOperation(value = "验证手机号是否存在", notes = "")
    @RequestMapping(value = "validataTel.do", method = RequestMethod.POST)
    public APIResponse validataTel(
            @ApiParam(name = "tel", value = "手机号", required = true)
            @RequestParam String tel
    ){
        return phoneUserService.validataTel(tel);
    }

    /**
     * 短信验证码
     * @param tel
     * @return
     */
    @ApiOperation(value = "获取手机验证码", notes = "")
    @RequestMapping(value = "smsCode.do", method = RequestMethod.POST)
    public APIResponse smsCode(
            @ApiParam(name = "tel", value = "手机号", required = true)
            @RequestParam String tel
    ){
        return phoneUserService.smsCode(tel);
    }

    /**
     * 手机端用户注册
     * @param userName
     * @param password
     * @param tel
     * @return
     */
    @ApiOperation(value = "手机用户注册", notes = "")
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public APIResponse register(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam String userName,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password,
            @ApiParam(name = "tel", value = "手机号", required = true)
            @RequestParam String tel,
            @ApiParam(name = "code", value = "验证码", required = true)
            @RequestParam String code,
            @ApiParam(name = "openId", value = "openId")
            @RequestParam(required = false) String openId
    ){
        return phoneUserService.register(userName, password, tel, code, openId);
    }


    @ApiOperation(value = "短信登录", notes = "")
    @RequestMapping(value = "smsLogin.do", method = RequestMethod.POST)
    public APIResponse smsLogin(
            HttpServletRequest request,
            @ApiParam(name = "tel", value = "手机号", required = true)
            @RequestParam String tel,
            @ApiParam(name = "code", value = "验证码", required = true)
            @RequestParam String code,
            @ApiParam(name = "openId", value = "openId")
            @RequestParam(required = false) String openId
    ){
        return phoneUserService.smsLogin(request, tel, code, openId);
    }


    /**
     * 手机登录
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @ApiOperation(value = "手机登录", notes = "")
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public APIResponse login(
            HttpServletRequest request,
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam String userName,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password,
            @ApiParam(name = "openId", value = "openId")
            @RequestParam(required = false) String openId
    ){
        return phoneUserService.login(request, userName, password, openId);
    }

    @ApiOperation(value = "退出", notes = "")
    @RequestMapping(value = "signout.do", method = RequestMethod.POST)
    public APIResponse signout(HttpServletRequest request){
        request.getSession().invalidate();
        return APIResponse.success("退出成功！");
    }

}
