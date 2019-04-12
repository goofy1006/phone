package com.shopping.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangas on 2018/8/16.
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);


    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static Date getEndDateTimeOfDay(Date date){
        try {
            String indexDate = dateFormat.format(date);
            indexDate = indexDate.concat(" 23:59:59");
            Date dateTime = dateTimeFormat.parse(indexDate);
            return dateTime;
        }catch (Exception e){
            logger.error(e.getMessage());
            return date;
        }

    }

}
