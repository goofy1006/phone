package com.shopping.service.impl;

import com.shopping.dao.EmBbRegionMapperExt;
import com.shopping.model.EmBbRegion;
import com.shopping.model.jsonbean.RegionJsonBean;
import com.shopping.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangas on 2018/7/26.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private EmBbRegionMapperExt emBbRegionMapperExt;

    @Override
    public List<RegionJsonBean> selectAllRegion() {
        //查询全部地区
        List<EmBbRegion> emBbRegionList = emBbRegionMapperExt.selectList(new EmBbRegion());
        //归类
        //key ： parent_region_id
        Map<String, List<RegionJsonBean>> mapRegionJsonBean = new HashMap<String, List<RegionJsonBean>>();
        if(CollectionUtils.isEmpty(emBbRegionList)){
          return null;
        }else{
            for(EmBbRegion emBbRegion : emBbRegionList){
                String parentRegionId = emBbRegion.getParentRegionId();
                if(!mapRegionJsonBean.containsKey(parentRegionId)){
                    List<RegionJsonBean> regionJsonBeanList = new ArrayList<RegionJsonBean>();
                    RegionJsonBean regionJsonBean = new RegionJsonBean();
                    regionJsonBean.setId(emBbRegion.getRegionId());
                    regionJsonBean.setName(emBbRegion.getRegionName());
                    regionJsonBeanList.add(regionJsonBean);
                    mapRegionJsonBean.put(parentRegionId, regionJsonBeanList);
                }else{
                    List<RegionJsonBean> regionJsonBeanList = mapRegionJsonBean.get(parentRegionId);
                    RegionJsonBean regionJsonBean = new RegionJsonBean();
                    regionJsonBean.setId(emBbRegion.getRegionId());
                    regionJsonBean.setName(emBbRegion.getRegionName());
                    regionJsonBeanList.add(regionJsonBean);
                }
            }
        }
        //查询中国所有的省
        List<RegionJsonBean> proviceRegionJsonBeanList = mapRegionJsonBean.get("G2");
        for(RegionJsonBean proviceRegionJsonBean : proviceRegionJsonBeanList){
            String proviceRegionId = proviceRegionJsonBean.getId();
            List<RegionJsonBean> cityRegionJsonBeanList = mapRegionJsonBean.get(proviceRegionId);
            proviceRegionJsonBean.setChild(cityRegionJsonBeanList);
            if(!CollectionUtils.isEmpty(cityRegionJsonBeanList)){
                for(RegionJsonBean cityRegionJsonBean : cityRegionJsonBeanList){
                    String cityRegionId = cityRegionJsonBean.getId();
                    List<RegionJsonBean> areaRegionJsonBeanList = mapRegionJsonBean.get(cityRegionId);
                    cityRegionJsonBean.setChild(areaRegionJsonBeanList);
                }
            }
        }
        return proviceRegionJsonBeanList;
    }
}


