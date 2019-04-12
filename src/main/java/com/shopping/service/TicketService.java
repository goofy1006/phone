package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.jsonbean.TicketJsonBean;
import com.shopping.model.jsonbean.TicketListJsonBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangas on 2018/8/11.
 */
public interface TicketService {

    APIResponse<List<TicketJsonBean>> ticketList(TicketJsonBean ticketJsonBean);

    APIResponse<List<TicketListJsonBean>> allTicketList(TicketListJsonBean ticketListJsonBean);

    APIResponse receive(String ticketId, String userId, String token);

    APIResponse getTicketListOnSubmitOrder(String[] contractGoodsIds, String userId, BigDecimal totalAmount);

    APIResponse calculatePrice(String[] contractGoodsIds, Integer[] numbers, String userId, String ticketId);

    Integer showTickets();

}
