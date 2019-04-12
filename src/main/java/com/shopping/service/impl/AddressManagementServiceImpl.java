package com.shopping.service.impl;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.alibaba.druid.util.StringUtils;
import com.shopping.common.UuidService;
import com.shopping.dao.EmCmAddressMapperExt;
import com.shopping.model.EmCmAddress;
import com.shopping.model.jsonbean.EmCmAddressJsonBean;
import com.shopping.service.IAddressManagementService;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/7/24.
 */
@Service
@Transactional
public class AddressManagementServiceImpl implements IAddressManagementService {

    private static Logger logger = LoggerFactory.getLogger(AddressManagementServiceImpl.class);

    @Autowired
    EmCmAddressMapperExt emCmAddressMapperExt;

    @Override
    public APIResponse<List<EmCmAddressJsonBean>> selectCmAddress(EmCmAddress emCmAddress) {
        List<EmCmAddressJsonBean> list = emCmAddressMapperExt.selectAddressList(emCmAddress);
        APIResponse<List<EmCmAddressJsonBean>> result = APIResponse.success(list);
        return result;
    }

    @Override
    public APIResponse insertAddress(EmCmAddress emCmAddress) {
        EmCmAddress emCmAddress1 = new EmCmAddress();
        emCmAddress1.setCustomerId(emCmAddress.getCustomerId());
        List<EmCmAddress> list = emCmAddressMapperExt.selectList(emCmAddress1);
        if(list == null || list.isEmpty()){//当第一条时默认为默认收货地址
            emCmAddress.setIsDefault((short) 1);
        }else if(emCmAddress.getIsDefault() == (short)1){//如果为默认收货地址，更新其他地址为非默认收货地址0
            emCmAddressMapperExt.updateIsDefault(emCmAddress.getCustomerId());
        }
        emCmAddressMapperExt.insertSelective(emCmAddress);
        return APIResponse.response(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse updateAddress(EmCmAddress emCmAddress) {
        logger.error(emCmAddress.getIsDefault()+"============");
        if(emCmAddress.getIsDefault().equals((short)1)){//默认收货地址，更新其他地址为非默认收货地址0
            logger.error("===========11111============");
            EmCmAddress emCmAddress1 = emCmAddressMapperExt.selectByPrimaryKey(emCmAddress.getAddressId());
            emCmAddressMapperExt.updateIsDefault(emCmAddress1.getCustomerId());
        }
        emCmAddressMapperExt.updateByPrimaryKeySelective(emCmAddress);
        return APIResponse.response(APIResponse.SUCCESS);
    }

    @Override
    public APIResponse deleAddress(String addressId) {
        emCmAddressMapperExt.deleteByPrimaryKey(addressId);
        return APIResponse.success(APIResponse.SUCCESS);
    }
}
