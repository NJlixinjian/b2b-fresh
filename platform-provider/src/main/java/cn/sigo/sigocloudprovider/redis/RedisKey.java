package cn.sigo.sigocloudprovider.redis;

public class RedisKey {
    //key失效时间 3600秒 1小时
    public static final Integer INVALID_ONE_HOUR = 3600;
    //key失效时间 1800秒 半小时
    public static final Integer INVALID_HALF_HOUR = 1800;
    //key失效时间 300秒 5分钟
    public static final Integer INVALID_FIVE_MINUTE = 300;
    //session key
    public static final String TOKEN_SESSION_KEY = "TokenSessionKey_";
    //记录登录失败次数
    public static final String LOGIN_COUNT_KEY = "LoginFailCount_";
    //记录验证码二次校验
    public static final String SHORT_MSG_CHECK = "ShortMsgCheck_";
    //禁用该账号
    public static final String LOGIN_PHONE_KEY = "DisablePhone_";
    //注册防止多次验证码发送
    public static final String REGISTER_SUCCESSCODE_KEY = "RegisterSuccessCode_";
    //图片验证码 -> 微商城手机短信登录方式
    public static final String MALL_VERIFI_SMS_LOGIN_KEY = "MallVerifiSMSLogin_";
    //图片验证码 -> 官网手机短信登录方式
    public static final String WEBSITE_VERIFI_SMS_LOGIN_KEY = "WebsiteVerifiSMSLogin_";


    //pc端注册验证码次数
    public static final String PC_REGISTER_PHONE_CODE_KEY = "pcRegisterPhoneCode_";
    //pc端手机短信登录
    public static final String PC_LOGIN_PHONE_CODE_KEY = "pcLoginPhoneCode_";
    //pc端忘记密码
    public static final String PC_PWD_PHONE_CODE_KEY = "pcPWDPhoneCode_";

    //seo 用户信息key
    public static final String SEO = "Seo_";

    //es  ip
    public static final String ES_IP_KEY = "es_ip_key";
    //es 用户 id
    public static final String ES_ACCOUNT_ID = "es_account_id_";






}
