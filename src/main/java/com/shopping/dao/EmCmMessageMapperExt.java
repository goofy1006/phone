package com.shopping.dao;

import javax.annotation.Resource;

@Resource
public interface EmCmMessageMapperExt extends EmCmMessageMapper {

    int selectIsRead(String customerId);
}