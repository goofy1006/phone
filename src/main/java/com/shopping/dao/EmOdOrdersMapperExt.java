package com.shopping.dao;

import com.shopping.model.EmOdOrders;
import com.shopping.model.jsonbean.EmOdOrdersJsonBean;
import com.shopping.model.jsonbean.OrderDetailJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface EmOdOrdersMapperExt extends EmOdOrdersMapper {

    List<EmOdOrdersJsonBean> selectListByStatus(EmOdOrders emOdOrders);

    void updateOrderStatus(EmOdOrders emOdOrders);

    List<OrderDetailJsonBean> selectOrderByOrderNo(String orderNo);

}