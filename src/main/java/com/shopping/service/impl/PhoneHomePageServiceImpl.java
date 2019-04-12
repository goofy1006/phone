package com.shopping.service.impl;

import cn.zjmiec.xks.core.mvc.APIResponse;
import com.shopping.dao.*;
import com.shopping.model.*;
import com.shopping.model.jsonbean.PhoneHomeGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeGoodsResultJsonBean;
import com.shopping.model.jsonbean.PhoneHomeHotGoodsJsonBean;
import com.shopping.model.jsonbean.PhoneHomeRankJsonBean;
import com.shopping.service.IPhoneHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/7/23.
 */
@Service
public class PhoneHomePageServiceImpl implements IPhoneHomePageService {
    @Autowired
    PhoneEmPpAdvertisementMapperExt phoneEmPpAdvertisementMapperExt;
    @Autowired
    PhoneEmPpNoticeMapperExt phoneEmPpNoticeMapperExt;
    @Autowired
    PhoneHomeRankMapperExt phoneHomeRankMapperExt;
    @Autowired
    PhoneHomeHotGoodsMapperExt phoneHomeHotGoodsMapperExt;
    @Autowired
    PhoneHomeGoodsMapperExt phoneHomeGoodsMapperExt;

    @Override
    public APIResponse<List<PhoneEmPpAdvertisement>> selectListAdvertisement() {
        List<PhoneEmPpAdvertisement> list = phoneEmPpAdvertisementMapperExt.selectList(new PhoneEmPpAdvertisement());
        APIResponse<List<PhoneEmPpAdvertisement>> result = APIResponse.success(list);
       // result.setData(list);
        return result;
    }

    @Override
    public APIResponse<List<PhoneEmPpNotice>> selectListNotice() {
        List<PhoneEmPpNotice> list = phoneEmPpNoticeMapperExt.selectList(new PhoneEmPpNotice());
        APIResponse<List<PhoneEmPpNotice>> result = APIResponse.success(list);
        return result;
    }

    @Override
    public APIResponse<List<PhoneHomeRankJsonBean>> selectListRank() {
        List<PhoneHomeRankJsonBean> list = phoneHomeRankMapperExt.selectListRank();
        APIResponse<List<PhoneHomeRankJsonBean>> result = APIResponse.success(list);
        return result;
    }

    @Override
    public APIResponse<List<PhoneHomeHotGoodsJsonBean>> selectListHotGoods() {
        List<PhoneHomeHotGoodsJsonBean> list = phoneHomeHotGoodsMapperExt.selectListHotGoods();
        APIResponse<List<PhoneHomeHotGoodsJsonBean>> result = APIResponse.success(list);
        return result;
    }

    @Override
    public APIResponse<List<PhoneHomeGoodsResultJsonBean>> selectListHomeGoods() {
        List<PhoneHomeGoodsResultJsonBean> list = new ArrayList<>();

       // List<PhoneHomeGoodsJsonBean> list = phoneHomeGoodsMapperExt.selectListHomeGoods();
        List<String> categoryCount = phoneHomeGoodsMapperExt.selectCountCategory();
        if(!CollectionUtils.isEmpty(categoryCount)){
            for(int i=0;i<categoryCount.size();i++){
                PhoneHomeGoodsResultJsonBean phoneHomeGoodsResultJsonBean = new PhoneHomeGoodsResultJsonBean();
                phoneHomeGoodsResultJsonBean.setCategoryId(categoryCount.get(i));
                List<PhoneHomeGoodsJsonBean> listPhoneHomeGoods = phoneHomeGoodsMapperExt.selectListHomeGoods(categoryCount.get(i));
                phoneHomeGoodsResultJsonBean.setCategoryName(listPhoneHomeGoods.get(0).getCategoryName());
                phoneHomeGoodsResultJsonBean.setPhoneHomeGoodsJsonBeans(listPhoneHomeGoods);
                list.add(phoneHomeGoodsResultJsonBean);
            }
        }
        APIResponse<List<PhoneHomeGoodsResultJsonBean>> result = APIResponse.success(list);
        return result;
    }
}
