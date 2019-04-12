package com.shopping.dao;

import com.shopping.model.EmCmAddress;
import com.shopping.model.jsonbean.EmCmAddressJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface EmCmAddressMapperExt extends EmCmAddressMapper {

    List<EmCmAddressJsonBean> selectAddressList(EmCmAddress emCmAddress);

    public void updateIsDefault(String customerId);
}