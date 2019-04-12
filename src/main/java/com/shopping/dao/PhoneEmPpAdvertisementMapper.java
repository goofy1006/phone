package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneEmPpAdvertisement;
import java.util.List;

public interface PhoneEmPpAdvertisementMapper extends BaseDao {
    int deleteByPrimaryKey(String advertisementId);

    int insert(PhoneEmPpAdvertisement record);

    int insertSelective(PhoneEmPpAdvertisement record);

    PhoneEmPpAdvertisement selectByPrimaryKey(String advertisementId);

    int updateByPrimaryKeySelective(PhoneEmPpAdvertisement record);

    int updateByPrimaryKey(PhoneEmPpAdvertisement record);

    List<PhoneEmPpAdvertisement> selectList(PhoneEmPpAdvertisement record);
}