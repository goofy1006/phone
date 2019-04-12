package com.shopping.service;

import com.shopping.model.EmBbRegion;
import com.shopping.model.jsonbean.RegionJsonBean;

import java.util.List;

/**
 * Created by zhangas on 2018/7/26.
 */
public interface RegionService {

    List<RegionJsonBean> selectAllRegion();

}
