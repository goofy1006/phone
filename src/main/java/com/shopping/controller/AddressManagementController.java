package com.shopping.controller;

import cn.zjmiec.xks.common.util.CommonUtil;
import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.model.EmBbRegion;
import com.shopping.model.EmCmAddress;
import com.shopping.model.PhoneUser;
import com.shopping.model.jsonbean.EmCmAddressJsonBean;
import com.shopping.service.IAddressManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/7/24.
 */
@Api(tags = "手机收货地址管理",value = "")
@RequestMapping(value = "/phoneAddress/")
@RestController
public class AddressManagementController extends BaseController{


    private static Logger logger = LoggerFactory.getLogger(AddressManagementController.class);

    @Autowired
    private IAddressManagementService iAddressManagementService;

    /**
     * 收货地址查询
     * @param customerId
     * @return
     */
    @ApiOperation(value = "收货地址查询")
    @RequestMapping(value = "selectAddress.do",method = RequestMethod.POST)
    public APIResponse<List<EmCmAddressJsonBean>> selectCmAddress(
           //@ApiParam(name = "EmCmAddress", value = "收货地址查询", required = true)
           @ApiParam(name = "customerId", value = "客户ID")
           @RequestParam(required = false) String customerId,
           @ApiParam(name = "addressId", value = "收货地址ID")
           @RequestParam(required = false) String addressId,
           @ApiParam(value = "是否是默认收货地址",name = "isDefault")
           @RequestParam Short isDefault
           //@ApiParam(name = "addressId", value = "收货地址ID", required = true)
           //@ModelAttribute EmCmAddress emCmAddress

    ){
        EmCmAddress emCmAddress = new EmCmAddress();
        if(!StringUtils.isEmpty(customerId)){
            emCmAddress.setCustomerId(customerId);
        }
        if(!StringUtils.isEmpty(addressId)){
            emCmAddress.setAddressId(addressId);
        }
        if(isDefault != null){
            emCmAddress.setIsDefault((isDefault));
        }
        return iAddressManagementService.selectCmAddress(emCmAddress);
    }

    /**
     * 收货地址新增
     * @param request
     * @param customerId
     * @param county
     * @param province
     * @param city
     * @param country
     * @param address
     * @param linkman
     * @param linkphone
     * @param isDefault
     * @return
     */
    @ApiOperation(value = "收货地址新增")
    @RequestMapping(value = "insertAddress.do",method = RequestMethod.POST)
    public APIResponse insertAddress(
           HttpServletRequest request,
           @ApiParam(name = "customerId", value = "客户主键", required = true)
           @RequestParam String customerId,
           @RequestParam(defaultValue = "G2") String county,
           @ApiParam(name = "province", value = "省份", required = true)
           @RequestParam String province,
           @ApiParam(name = "city", value = "城市", required = true)
           @RequestParam String city,
           @ApiParam(name = "country", value = "区", required = true)
           @RequestParam String country,
           @ApiParam(name = "address", value = "详细地址", required = true)
           @RequestParam String address,
           @ApiParam(name = "linkman",value = "联系人", required = true)
           @RequestParam String linkman,
           @ApiParam(name = "linkphone", value = "手机号", required = true)
           @RequestParam String linkphone,
           @ApiParam(name = "isDefault", value = "是否默认", required = true)
           @RequestParam Integer isDefault

    ){
        EmCmAddress emCmAddress = new EmCmAddress();
        emCmAddress.setAddressId(CommonUtil.getUUID());
        emCmAddress.setCustomerId(customerId);
        emCmAddress.setCounty(county);
        emCmAddress.setProvince(province);
        emCmAddress.setCountry(country);
        emCmAddress.setCity(city);
        emCmAddress.setAddress(address);
        emCmAddress.setLinkman(linkman);
        emCmAddress.setLinkphone(linkphone);
        emCmAddress.setIsDefault(Short.valueOf(String.valueOf(isDefault)));
        emCmAddress.setCreatedTime(new Date());
        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if(object != null){
            PhoneUser phoneUser = (PhoneUser)object;
            logger.debug("=====>"+ phoneUser.getUserName());
        }
        return iAddressManagementService.insertAddress(emCmAddress);
    }

    /**
     * 收货地址修改
     * @param addressId
     * @param county
     * @param province
     * @param city
     * @param country
     * @param address
     * @param linkman
     * @param linkphone
     * @param isDefault
     * @return
     */
    @ApiOperation(value = "收货地址修改")
    @RequestMapping(value = "updateAddress.do",method = RequestMethod.POST)
    public APIResponse updateAddress(
            @ApiParam(name = "addressId", value = "地址主键", required = true)
            @RequestParam String addressId,
            @RequestParam(defaultValue = "G2") String county,
            @ApiParam(name = "province", value = "省份")
            @RequestParam(required = false) String province,
            @ApiParam(name = "city", value = "城市")
            @RequestParam(required = false) String city,
            @ApiParam(name = "country", value = "区")
            @RequestParam(required = false) String country,
            @ApiParam(name = "address", value = "详细地址")
            @RequestParam(required = false) String address,
            @ApiParam(name = "linkman",value = "联系人")
            @RequestParam(required = false) String linkman,
            @ApiParam(name = "linkphone", value = "手机号")
            @RequestParam(required = false) String linkphone,
            @ApiParam(name = "isDefault", value = "是否默认")
            @RequestParam(required = false) Integer isDefault
    ){
        EmCmAddress emCmAddress = new EmCmAddress();
        emCmAddress.setAddressId(addressId);
        emCmAddress.setCounty(county);
        if(!StringUtils.isEmpty(province)){
            emCmAddress.setProvince(province);
        }
        if(!StringUtils.isEmpty(country)){
            emCmAddress.setCountry(country);
        }
        if(!StringUtils.isEmpty(city)){
            emCmAddress.setCity(city);
        }
        if(!StringUtils.isEmpty(address)){
            emCmAddress.setAddress(address);
        }
        if(!StringUtils.isEmpty(linkman)){
            emCmAddress.setLinkman(linkman);
        }
        if(!StringUtils.isEmpty(linkphone)){
            emCmAddress.setLinkphone(linkphone);
        }
        if(isDefault != null){
            emCmAddress.setIsDefault(Short.valueOf(String.valueOf(isDefault)));
        }
        emCmAddress.setUpdatedTime(new Date());
        return iAddressManagementService.updateAddress(emCmAddress);
    }

    /**
     * 收货地址删除
     * @param addressId
     * @return
     */
    @ApiOperation(value = "收货地址删除")
    @RequestMapping(value = "deleAddress.do",method = RequestMethod.POST)
    public @ResponseBody APIResponse deleAddress(
            @ApiParam(name = "addressId",value = "地址ID",required = true)
            @RequestParam String addressId
    ){
        return iAddressManagementService.deleAddress(addressId);
    }

}
