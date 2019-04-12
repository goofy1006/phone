package com.shopping.dao;

import cn.zjmiec.xks.common.plugin.BaseDao;
import com.shopping.model.PhoneEmPpNotice;
import java.util.List;

public interface PhoneEmPpNoticeMapper extends BaseDao {
    int deleteByPrimaryKey(String noticeId);

    int insert(PhoneEmPpNotice record);

    int insertSelective(PhoneEmPpNotice record);

    PhoneEmPpNotice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(PhoneEmPpNotice record);

    int updateByPrimaryKey(PhoneEmPpNotice record);

    List<PhoneEmPpNotice> selectList(PhoneEmPpNotice record);
}