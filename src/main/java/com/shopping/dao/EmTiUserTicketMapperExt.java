package com.shopping.dao;

import com.shopping.model.jsonbean.TicketJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface EmTiUserTicketMapperExt extends EmTiUserTicketMapper {

    List<TicketJsonBean> ticketList(TicketJsonBean ticketJsonBean);

    List<String> selectCategoryNameByticketId(String ticketId);

    List<String> selectTickUserId(String orderId);

}