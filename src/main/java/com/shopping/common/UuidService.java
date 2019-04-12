package com.shopping.common;

import java.util.UUID;

/**
 * Created by lenovo on 2018/7/24.
 */
public class UuidService {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
}
