package com.shopping.controller;

import com.shopping.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangas on 2018/8/16.
 */
@Api(tags = "代金券验证是否能够显示")
@RestController
@RequestMapping(value = "/validata/")
public class TicketValidataController extends BaseController{

    @Autowired
    private TicketService ticketService;

    @ApiOperation(value = "判断是否显示大礼包", notes = "")
    @RequestMapping(value = "showTickets.do",method = RequestMethod.POST)
    public Integer showTickets(){
        return ticketService.showTickets();
    }

}
