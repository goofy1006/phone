package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmMessage;
import com.shopping.model.PhoneCommonQuestion;
import com.shopping.model.PhoneCompanyMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/8/2.
 */
public interface IEmCmMessageService {

    /**
     * 我的消息查看
     * @param customerId
     * @return
     */
    public APIResponse<List<EmCmMessage>> selectListMessage(String customerId,Integer pageSize,Integer pageNum);

    /**
     * 我的消息删除
     * @param messageId
     * @return
     */
    public APIResponse delMessage(String messageId);

    /**
     *修改消息为已读
     * @param messageId
     * @return
     */
    public APIResponse updateMessageRead(String messageId);

    /**
     * 查询售后服务电话
     * @return
     */
    public APIResponse<PhoneCompanyMessage> selectCompanyMessage();

    /**
     * 常见问题查询
     * @return
     */
    public APIResponse<List<PhoneCommonQuestion>> selectListQuestion(Integer pageSize,Integer pageNum);

    public APIResponse insertEmCmMessage(String customerId,String messgeTitle,String messageDesc);

    public APIResponse<Integer> selectIsRead(String customerId);
}
