package com.shopping.service;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmCmAddress;
import com.shopping.model.jsonbean.EmCmAddressJsonBean;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lenovo on 2018/7/24.
 */
public interface IAddressManagementService {

    /**
     * 收货地址查询
     * @param emCmAddress
     * @return
     */
    public APIResponse<List<EmCmAddressJsonBean>> selectCmAddress(EmCmAddress emCmAddress);

    /**
     * 收货地址新增
     * @param emCmAddress
     * @return
     */
    public APIResponse insertAddress(EmCmAddress emCmAddress);

    /**
     * 收货地址修改
     * @param emCmAddress
     * @return
     */
    public APIResponse updateAddress(EmCmAddress emCmAddress);

    /**
     * 收货地址删除
     * @param addressId
     * @return
     */
    public APIResponse deleAddress(String addressId);
}
