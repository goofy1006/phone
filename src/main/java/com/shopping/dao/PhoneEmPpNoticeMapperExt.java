package com.shopping.dao;

import com.shopping.model.PhoneEmPpNotice;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PhoneEmPpNoticeMapperExt extends PhoneEmPpNoticeMapper {

    /**
     * 首页头条配置信息查询
     * @return
     */
    List<PhoneEmPpNotice> selectListNotice();
}