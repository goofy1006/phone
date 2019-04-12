package com.shopping.service.impl;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.dao.EmCmMessageMapperExt;
import com.shopping.dao.PhoneCommonQuestionMapperExt;
import com.shopping.dao.PhoneCompanyMessageMapperExt;
import com.shopping.model.EmCmMessage;
import com.shopping.model.PhoneCommonQuestion;
import com.shopping.model.PhoneCompanyMessage;
import com.shopping.service.IEmCmMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/8/2.
 */
@Service
public class EmCmMessageServiceImpl implements IEmCmMessageService {
    @Autowired
    EmCmMessageMapperExt emCmMessageMapperExt;

    @Autowired
    PhoneCompanyMessageMapperExt phoneCompanyMessageMapperExt;
    @Autowired
    PhoneCommonQuestionMapperExt phoneCommonQuestionMapperExt;

    @Override
    public APIResponse<List<EmCmMessage>> selectListMessage(String customerId,Integer pageSize,Integer pageNum) {
        APIResponse<List<EmCmMessage>> result = new APIResponse<>();
            EmCmMessage emCmMessage = new EmCmMessage();
            emCmMessage.setCustomerId(customerId);
            PageHelper.startPage(pageNum,pageSize,"created_time desc");
            List<EmCmMessage> list = emCmMessageMapperExt.selectList(emCmMessage);
            PageInfo<EmCmMessage> info = new PageInfo<>(list);
        result.setData(list);
        result.setTotal((int)info.getTotal());
        result.setCode(APIResponse.SUCCESS);

        return result;
    }

    @Override
    public APIResponse delMessage(String messageId) {
        emCmMessageMapperExt.deleteByPrimaryKey(messageId);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse updateMessageRead(String messageId) {
        EmCmMessage emCmMessage = new EmCmMessage();
        emCmMessage.setMessageId(messageId);
        emCmMessage.setIsRead((short) 1);
        emCmMessage.setReadTime(new Date());
        emCmMessage.setUpdatedTime(new Date());
        emCmMessageMapperExt.updateByPrimaryKeySelective(emCmMessage);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse<PhoneCompanyMessage> selectCompanyMessage() {
        List<PhoneCompanyMessage> list = phoneCompanyMessageMapperExt.selectList(new PhoneCompanyMessage());
        return APIResponse.success(list.get(0));
    }

    @Override
    public APIResponse<List<PhoneCommonQuestion>> selectListQuestion(Integer pageSize,Integer pageNum) {
        APIResponse<List<PhoneCommonQuestion>> result = new APIResponse<>();
        PageHelper.startPage(pageNum,pageSize,"create_time desc");
        List<PhoneCommonQuestion> list = phoneCommonQuestionMapperExt.selectList(new PhoneCommonQuestion());
        PageInfo<PhoneCommonQuestion> info = new PageInfo<>(list);
        result.setCode(APIResponse.SUCCESS);
        result.setData(list);
        result.setTotal((int)info.getTotal());
        return result;
    }

    @Override
    public APIResponse insertEmCmMessage(String customerId, String messgeTitle, String messageDesc) {
        EmCmMessage emCmMessage = new EmCmMessage();
        emCmMessage.setMessageId(CommonUtil.getUUID());
        emCmMessage.setCustomerId(customerId);
        emCmMessage.setMessageTitle(messgeTitle);
        emCmMessage.setSendTime(new Date());
        emCmMessage.setIsRead((short)0);//未阅读
        emCmMessage.setMessageDesc(messageDesc);
        emCmMessage.setCreatedTime(new Date());
        emCmMessage.setCreatedBy(customerId);
        emCmMessage.setIsInactive((short)0);//未停用
         emCmMessageMapperExt.insertSelective(emCmMessage);
        return APIResponse.success(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse<Integer> selectIsRead(String customerId) {
        int count = emCmMessageMapperExt.selectIsRead(customerId);
        return APIResponse.success(count);
    }
}
