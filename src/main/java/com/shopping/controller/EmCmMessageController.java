package com.shopping.controller;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmMessage;
import com.shopping.model.PhoneCommonQuestion;
import com.shopping.model.PhoneCompanyMessage;
import com.shopping.service.IEmCmMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/8/2.
 */
@Controller
@Api(tags = "我的消息管理")
@RequestMapping(value = "/message/")
public class EmCmMessageController {

    @Autowired
    IEmCmMessageService iEmCmMessageService;

    /**
     * 我的消息查看
     * @param customerId
     * @return
     */
    @ApiOperation(value = "我的消息查看")
    @RequestMapping(value = "selectListMessage.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<EmCmMessage>> selectListMessage(
            @ApiParam(value = "用户ID",name = "customerId",required = true)
            @RequestParam String customerId,
            @ApiParam(value = "每页显示条数",name = "pageSize",required = true)
            @RequestParam Integer pageSize,
            @ApiParam(value = "页码",name = "pageNum",required = true)
            @RequestParam Integer pageNum
    ){
            return iEmCmMessageService.selectListMessage(customerId,pageSize,pageNum);
    }

    /**
     * 我的消息删除
     * @param messageId
     * @return
     */
    @ApiOperation(value = "我的消息删除")
    @RequestMapping(value = "delMessage",method = RequestMethod.POST)
    public @ResponseBody APIResponse delMessage(
            @ApiParam(value = "消息ID",name = "messageId")
            @RequestParam String messageId
    ){
            return iEmCmMessageService.delMessage(messageId);
    }

    /**
     * 修改消息为已读
     * @param messageId
     * @return
     */
    @ApiOperation(value = "修改消息为已读")
    @RequestMapping(value = "updateMessageRead.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse updateMessageRead(
            @ApiParam(value = "消息ID",name = "messageId")
            @RequestParam String messageId
    ){
            return iEmCmMessageService.updateMessageRead(messageId);
    }

    /**
     * 查询售后电话信息
     * @return
     */
    @ApiOperation(value = "查询售后电话信息")
    @RequestMapping(value = "selectCompanyMessage.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<PhoneCompanyMessage> selectCompanyMessage(){
            return iEmCmMessageService.selectCompanyMessage();
    }

    /**
     * 常见问题查询
     * @return
     */
    @ApiOperation(value = "常见问题查询")
    @RequestMapping(value = "selectListQuestion.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse<List<PhoneCommonQuestion>> selectListQuestion(
            @ApiParam(value = "每页显示条数",name = "pageSize",required = true)
            @RequestParam Integer pageSize,
            @ApiParam(value = "页码",name = "pageNum",required = true)
            @RequestParam Integer pageNum
    ){
        return iEmCmMessageService.selectListQuestion(pageSize,pageNum);
    }

    /**
     * 客户消息插入
     * @param customerId
     * @param messgeTitle
     * @param messageDesc
     * @return
     */
    @ApiOperation(value = "客户消息插入")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public @ResponseBody APIResponse insertEmCmMessage(
            @ApiParam(value = "客户ID",name = "customerId",required = true)
            @RequestParam String customerId,
            @ApiParam(value = "消息摘要",name = "messgeTitle",required = true)
            @RequestParam String messgeTitle,
            @ApiParam(value = "详细详述",name = "messageDesc",required = true)
            @RequestParam String messageDesc
    ){
         return iEmCmMessageService.insertEmCmMessage(customerId,messgeTitle,messageDesc);
    }

    @ApiOperation(value = "检索是否有未读消息")
    @RequestMapping(value = "selectIsRead",method = RequestMethod.POST)
    public @ResponseBody APIResponse<Integer> selectIsRead(
            @ApiParam(name = "customerId",value = "用户ID")
            @RequestParam String customerId
    ){
            return iEmCmMessageService.selectIsRead(customerId);
    }
}
