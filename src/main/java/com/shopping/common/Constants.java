package com.shopping.common;

/**
 * Created by zhangas on 2018/8/12.
 */
public interface Constants {

    //券类型
    public static final class TicketType{

        public static final Integer CASH_COUPON = 1;//代金券

        public static final Integer DISCOUNT_COUPON  = 2;//折扣券

    }

    //有效期类型
    public static final class ValidType{

        public static final Integer FIXED_VALUE = 1;//固定有效期

        public static final Integer N_DAY_AVAILABILITY = 2;//领券N天可用

    }

    //代金券使用情况
    public static final class TicketIsUse{

        public static final Integer NOT_USE = 1;//未使用

        public static final Integer HAD_USED = 2;//已使用

        public static final Integer BE_OVERDUE = 3;// 已过期

    }

    //使用范围
    public static final class UseScope{

        public static final Integer COMMON_PUBLIC = 1;//全场通过用

        public static final Integer BY_CATEGOTY = 2;//按品类

    }

    //使用条件
    public static final class UseCondition{

        public static final Integer FULL_SUBTRACTION = 1;//满xx元可用

        public static final Integer UNLIMITED = 2;//无限制

    }

    //发放方式
    public static final class IssueWay{

        public static final Integer MANUAL_RECEIPT = 1;//手动领取

        public static final Integer AUTO_RECEIPT = 2;//自动发放

    }

    //是否发放
    public static final class IsIssue{

        public static final Integer ALREADY_ISSUED = 1;

        public static final Integer NOT_ISSUED = 0;

    }

    //投放对象
    public static final class SendUser{

        public static final Integer NEW_USER = 1;//新用户

        public static final Integer ORDER_USER = 2;//下单用户

        public static final Integer ALL_USER = 3;//所有用户

        public static final Integer WECHAT_SHARE_USER = 4;//微信分享用户

    }

}
