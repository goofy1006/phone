package com.shopping.dao;

import com.shopping.model.EmTiTicket;
import com.shopping.model.jsonbean.TicketListJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface EmTiTicketMapperExt extends EmTiTicketMapper {

    List<TicketListJsonBean> allTicketList();

    List<EmTiTicket> getTicketListByCategoryIds(List<String> categoryIds);

    int updateTicketMonNum(EmTiTicket emTiTicket);

}