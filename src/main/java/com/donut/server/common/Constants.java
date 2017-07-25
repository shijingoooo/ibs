package com.donut.server.common;

import java.util.Arrays;
import java.util.List;

public class Constants
{
    /*
     * 请在此定义各个模块使用的常量定义，模块以块注释开头，单常量以行注释开头
     */

    // 返回异常(1：异常)
    public static final String ERR_CODE = "500";

    // 未知区域
    public static final String AREA_CODE_UNKNOWN = "000000";

    // 返回异常(异常信息)
    public static final String ERR_MSG = "系统异常！";

    // 评论类型（0：热门评论）
    public static final int comment_0 = 0;

    // 评论类型（1：普通评论）
    public static final int comment_1 = 1;

    // SESSION_用户ID
    public static final String SESSION_USERID = "userId";

    // SESSION_用户名
    public static final String SESSION_USERNAME = "userName";

    // SESSION_用户角色名称
    public static final String SESSION_USERROLE = "userRole";

    public static final int USERTYPE_ADMIN = 0;

    public static final int USERTYPE_OPERATOR = 1;

    // SESSION_登录ID
    public static final String SESSION_LOGIN_USERID = "loginUserId";

    // 时间FORMART yyyy/mm/dd hh:mm:ss
    public final static String DATE_TIME_LOCALE = "yyyy-MM-dd HH:mm:ss";

    // 时间FORMART yyyy/mm/dd hh:mm:ss
    public final static String DATE_TIME_LOCALE_MS = "yyyy-MM-dd HH:mm:ss.SSS";

    // 时间FORMART yyyy-mm-dd hh:mm:ss
    public final static String DATE_TIME_LOCALE_1 = "yyyy/MM/dd HH:mm:ss";

    // 时间FORMART yyyy/mm/dd
    public final static String DATE_TIME_LOCALE_YMD = "yyyy-MM-dd";

    // 时间FORMART yyyyMMddHHmmss
    public final static String DATE_TIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    // APP存放路径
    public final static String APP_UPDATE_APPSTORE = "/appstore/";

    // 工程名
    public final static String WORK_NAME = "Plank";

    // 工程URL
    public final static String BASE_URL = "http://localhost:8080";

    // 支付时是否返还
    public final static String IS_RETURN = "is_return";

    // 返回比例
    public final static String RETURN_RATIO = "return_ratio";

    // 考试类型，0免费考试，1收费考试
    public final static String FREE_EXAM = "0";

    public final static String CHARGE_EXAM = "1";

    // 空格
    public final static String BLANK = " ";

    // 时间FORMART 00:00:00
    public final static String ZERO_TIME = "00:00:00";

    public final static Integer PUBLISH_STATUS = 1;

    public final static Integer DISPUBLISH_STATUS = 0;

    public final static String VALIDATE_SUCCESS = "200";

    public final static int SYSTEM_MANAGER_USERTYPE = 2;

    public final static int PUBLISHER_USERTYPE = 1;

    public final static String ANDROID_ADDRESS = "https://webapi.sms.mob.com/sms/verify";

    public final static String IPHONE_ADDRESS = "https://webapi.sms.mob.com/sms/verify";

    public final static String ANDROID_APPKEY = "15703b619039b";

    public final static String IPHONE_APPKEY = "157056ec7e538";

    /**
     * 标签组 0：全部 1：学生 2：教练
     */
    public final static String PUSH_MSG_CODE_ALL0 = "0";

    public final static String PUSH_MSG_CODE_STUDENT1 = "1";

    public final static String PUSH_MSG_CODE_PUBLISHER2 = "2";

    /**
     * 标签组 0：明星 1：粉丝 2：未登录
     */
    public final static String PUSH_MSG_CODE_STAR = "0";

    public final static String PUSH_MSG_CODE_FANS = "1";

    public final static String PUSH_MSG_CODE_NOTLOG = "2";

    /**
     * 用户类型 0:明星 1：粉丝
     */
    public final static int USER_TYPE_STAR = 0;

    public final static int USER_TYPE_FANS = 1;

    /**
     * 操作系统类型 1：IOS 0：Android和其他
     */
    public final static String OSTYPE_IOS1 = "1";

    public final static String OSTYPE_ANDROIDOTHER0 = "0";

    public final static int USER_ADMIN_STATUS_ENABLE = 0;

    public final static int USER_ADMIN_STATUS_DISABLE = 1;

    /**
     * 广告类型 0：url链接类广告 1：课程类广告
     */
    public final static int AD_TYPE_URL0 = 0;

    public final static int AD_TYPE_COURSE1 = 1;

    public static final String ADMIN_USER_DEFAULT_PASSWD = "afdd0b4ad2ec172c586e2150770fbf9e";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_ACTOR = "ROLE_ACTOR";

    public static final String ROLE_DEMANDER = "ROLE_DEMANDER";

    public final static int USER_ADMIN_TYPE_SUPER = 0;

    public final static int USER_ADMIN_TYPE_NORMAL = 1;

    public final static int USER_REGISTER_TYPE_COMMON = 0;

    public final static int USER_TIME_TRAKC_LOG_ADD_COMMON = 0;

    public final static int USER_TIME_TRAKC_LOG_ADD_CHALLENGE = 1;

    public final static int USER_TIME_TRAKC_LOG_QUERY_HIGHEST = 0;

    public final static int USER_TIME_TRAKC_LOG_QUERY_SUM = 1;

    public final static int USER_TYPE_ADMIN = 0;

    public final static int USER_TYPE_USER = 1;

    public final static int RECORDED_NUMBER = 10;

    public final static int F04_AD_INFO_TYPE_URL = 0;

    public final static int F04_AD_INFO_TYPE_COURSE = 1;

    public final static int COURSE_COLLECTION_TYPE_ADD = 0;

    public final static int COURSE_COLLECTION_TYPE_DELETE = 1;

    public final static int ADVISE_TYPE_COMMON = 0;

    public final static int COURSE_EXAM_PAY_TYPE_FREE = 0;

    public final static String USER_INFO_USER_ID = "USER_ID";

    public final static String USER_INFO_USER_NAME = "USER_NAME";

    public final static int SCORE_RECORD_SIGNIN = 0;

    public final static int SCORE_SIGNIN = 10;

    public final static String FILE_UPLOAD_PROCESS = "FILE_UPLOAD_PROCESS";

    public final static String SMB_SERVICE_URL = "SMB_SERVICE_URL";

    public final static String MEDIA_SERVICE_URL = "MEDIA_SERVICE_URL";

    public final static String TEMPORARY_FILE_PATH = "TEMPORARY_FILE_PATH";

    public final static String SMS_VERIFY_SWITCH_KEY = "SMS_VERIFY_SWITCH";

    public final static String SMS_VERIFY_SWITCH_ON = "0";

    public final static String SMS_VERIFY_SWITCH_OFF = "1";

    public final static String MQ_RATIO = "MQ_RATIO";

    public final static String RECHARGE_RATIO = "RECHARGE_RATIO";

    public final static String STAR_SPLIT_RATIO = "STAR_SPLIT_RATIO";

    public final static String USER_SPLIT_RATIO = "USER_SPLIT_RATIO";

    public final static String COMMENT_MAX_VALUE = "COMMENT_MAX_VALUE";

    public final static String PRAISE_MAX_VALUE = "PRAISE_MAX_VALUE";

    /**
     * '0：表示正常，未启用 1：表示正常，启用 5：表示删除，中间234预留';
     */
    public final static Integer CARD_STATUS_NOMAL = 0;

    public final static Integer CARD_STATUS_DEFAULT = 1;

    public final static Integer CARD_STATUS_DELETE = 5;

    /**
     * 3:选中 0：未选中
     */
    public final static Integer LABEL_STATUS_CHECK = 3;

    public final static Integer LABEL_STATUS_NOCHECK = 0;

    /**
     * label type 0:apprance 1:skill
     */
    public final static int LABEL_TYPE_APP = 0;

    public final static Integer LABEL_TYPE_SKILL = 1;

    public final static Integer LABEL_TYPE_All = 2;

    public final static Integer LABEL_USERTYPE_ACTOR = 0;

    public final static Integer LABEL_USERTYPE_NEED = 1;

    // solr索引项目
    public static final String SOLRKEY_CONTENT = "content";

    public static final String SOLRKEY_TITLE = "title";

    public static final String SOLRKEY_URL = "url";

    public static final String SOLRKEY_IPADRESS = "ipadress";

    public static final String SOLRKEY_DOMAINNAME = "domainname";

    public static final String SOLRKEY_TIMESTAMP = "tstamp";

    public static final String SOLRKEY_PAGESTATUS = "pagestatus";

    public static final String SOLRKEY_SEGMENT = "segment";

    public static final String SOLRKEY_PLAYLINKS = "playlinks";

    public static final List<Integer> WEIGHT_LIST = Arrays.asList(45, 55, 65);

    public static final List<Integer> HEIGHT_LIST = Arrays
            .asList(160, 170, 180);

    public static final List<Integer> AGE_LIST = Arrays.asList(20, 30, 40);

    public static final List<Integer> WORK_YEAR_LIST = Arrays.asList(1, 2, 3);

    public static final String LABEL_SEPARATOR = ";";

    // 方案有效 0
    public static final Integer IS_VALID = 0;

    /**
     * content_type '0:专题 1:专题历史 2:挑战 3:IP征集';
     */
    public static final Integer CONTENT_TYPE_PK_SUBJECT = 0;

    public static final Integer CONTENT_TYPE_PK_HISTORY = 1;

    public static final Integer CONTENT_TYPE_PK_CHALLENGE = 2;

    public static final Integer CONTENT_TYPE_IP = 3;

    public static final Integer CONTENT_TYPE_WISH = 4;

    public static final Integer CONTENT_TYPE_SNAP = 5;

    public static final Integer CONTENT_TYPE_STAR_NOTICE = 7;

    // 推送组播名称
    public static final String STAR_TAG_ANDROID = "star_android";

    public static final String FANS_TAG_ANDROID = "fans_android";

    public static final String NOTLOG_TAG_ANDROID = "notlog_android";

    public static final String STAR_TAG_IOS = "star_ios";

    public static final String FANS_TAG_IOS = "fans_ios";

    public static final String NOTLOG_TAG_IOS = "notlog_ios";

    public static final String COLLECTION_NAME_COMMENT = "comment";

    public static final String COLLECTION_NAME_HOTWORDS = "hotwords";

    public static final String COLLECTION_NAME_CONDITIONHOTWORDS = "conditionhotwords";

    public static final String COLLECTION_NAME_USER_BEHAVIOUR = "user_behaviour";

    public static final String COLLECTION_NAME_USER_ADD = "user_add";

    public static final String COLLECTION_NAME_CONTENT_BROWSE = "content_browse";

    public static final String COLLECTION_NAME_PAGE_NAME = "page_name";

    public static final String COLLECTION_NAME_BROWSE_TIMES = "browse_times";

    public static final String COLLECTION_NAME_PAGE_ATTRACT = "page_attract";

    public static final String COLLECTION_NAME_START_INFO = "start_info";

    public static final String UPDATE_SET = "set";

    public static final String UPDATE_PUSH = "push";

    public static final String UPDATE_INC = "inc";

    public static final String UPDATE_PULL = "pull";

    public static final String UPDATE_ADDTOSET = "addToSet";

    // 快捷导入配置器key
    public static final String ACTIVATE_URL = "ACTIVATE_URL";

    public static final int DO1ORDER_STATUS_SHOPPINGCARD = 2;

    public static final int DO1ORDER_STATUS_APPROMOTION = 4;

    public static final int MEMBER_STATUS_MEMBER = 1;

    // 跳转页面noticeType
    public static final String NOTICE_CENTER = "0001";

    public static final String ORDER_DETAILS = "0501";

    public static final String IP_LIST = "0401";

    public static final String PUSH_GOODS_DETAIL = "0201";

    public static final int D02GOODS_PUBLISH_MEMBER_AREA = 1;

    public static final String D02GOODS_PUBLISH_MEMBER_AREA_TEXT = "商品专区";

    public static final int D02GOODS_TYPE_GOOD_REAL = 0;

    public static String D01_KEY_TOTAL_AMOUNT = "total_amount";

    public static String D01_KEY_TOTAL_FREIGHT = "total_freight";

    public static String OPERATION_DATA_ENLARGE_NUM = "OPERATION_DATA_ENLARGE_NUM";

    public static final int GOODS_CONTIDION_NO_CONDITION = 0;

    public static final int GOODS_CONTIDION_HAVE_CHALLENGE = 1;

    public static final int GOODS_CONTIDION_HAVE_IDEA = 2;

    public static final int GOODS_CONTIDION_CHALLENGE_TOP = 3;

    public static final int GOODS_CONTIDION_IDEA_TOP = 4;

    public static final String GOODS_CONTIDION_KEY_NUM_CHALLENGE_UPS = "NUM_CHALLENGE_UPS";

    public static final String GOODS_CONTIDION_KEY_NUM_IDEA_UPS = "NUM_IDEA_UPS";

    public static final String GOODS_CONTIDION_KEY_SUBJECT_CHALLENGE_TOPN = "SUBJECT_CHALLENGE_TOPN";

    public static final int RESULT_CHECK_GOOD_VALID_SUCCESS = -1;

    public static final int GOODS_KIND_AUCTION_YES = 1; // 代表竞拍商品

    public static final int RESULT_CHECK_GOOD_NUM_NOT_ENOUGH_ERROR = 0;

    public static final int RESULT_CHECK_GOOD_HAVE_BEEN_BUY_ERROR = 1;

    public static final int RESULT_CHECK_GOOD_MAX_BUY_NUM_ERROR = 2;

    public static final int RESULT_CHECK_GOOD_CHALLENGE_CONDITION_ERROR = 3;

    public static final int RESULT_CHECK_GOOD_IDEA_CONDITION_ERROR = 4;

    public static final int RESULT_CHECK_GOOD_TOPN_CONDITION_ERROR = 5;

    // 未领取活动
    public static final int PROMOTION_OBTAIN_STATUS_NO = 0;

    // 已经领取活动
    public static final int PROMOTION_OBTAIN_STATUS_YES = 1;

    // 提交心愿、意见反馈的邮件人
    public final static String TO_MAIL = "TO_MAIL";

    // 粉丝洗漱
    public final static String COEFFICIENT_FANS = "COEFFICIENT_FANS";

    public static final String HOME_PAGE_PIC = "HOME_PAGE_PIC";

    public static final String DEFAULT_SUBJECT_CHANNEL_ID = "DEFAULT_SUBJECT_CHANNEL_ID";

    // 竞拍去付款倒计时分钟
    public static final String PAYCOUNTDOWN_MINUTE = "PAYCOUNTDOWN_MINUTE";

    public static String getContentNameByType(Integer type)
    {
        String name = "";
        switch (type)
        {
        case 0:
            name = "PK专题";
            break;
        case 1:
            name = "PK专题";
            break;
        case 2:
            name = "PK挑战";
            break;
        case 3:
            name = "创意";
            break;
        case 4:
            name = "心愿";
            break;
        case 5:
            name = "街拍专题";
            break;
        case 7:
            name = "大咖有通告";
            break;
        }
        return name;
    }
}
