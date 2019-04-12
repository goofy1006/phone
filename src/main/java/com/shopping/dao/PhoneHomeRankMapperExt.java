package com.shopping.dao;

import com.shopping.model.PhoneHomeRank;
import com.shopping.model.jsonbean.PhoneHomeRankJsonBean;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PhoneHomeRankMapperExt extends PhoneHomeRankMapper {

    /**
     * 首页排行榜查询
     * @return
     */
    List<PhoneHomeRankJsonBean> selectListRank();
}