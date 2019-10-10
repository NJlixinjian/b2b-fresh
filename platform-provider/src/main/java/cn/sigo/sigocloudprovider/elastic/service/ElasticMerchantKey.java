package cn.sigo.sigocloudprovider.elastic.service;

/**
 * @Auther: lxj
 * @Date: 2019/9/24 01:22
 * @Description:
 */
public class ElasticMerchantKey {

    public static final String CLUSTER_NAME = "cluster.name";
    public static final String DELETE_SUCCEED = "OK";
    public static final String INDEX_MERCHANT_SHOP = "index_merchant_shop";//商家索引
    public static final String MERCHANT_TYPE = "_doc";//索引类型
    public static final String SHOP_CODE = "account.user_name";//商户账号
    public static final String ACCOUNT_PHONE = "account.phone";//注册手机号
    public static final String SHOP_NAME ="shop_name";//店铺名称

    public static final String CONTACT_NAME = "contact.name.keyword";//联系人
    public static final String CONTACT_PHONE = "contact.phone";//联系电话
    public static final String AREA_MANAGER = "area_manager";//区域经理
    public static final String BUSINESS_LICENSE_CODE = "entity.business_license_code";//执照编码
    public static final String PROVINCE_CODE = "shop_area.province.code";//省编码
    public static final String CITY_CODE = "shop_area.city.code";//市编码
    public static final String BEGIN_REG_TIME = "reg_time.keyword";//注册时间
    public static final String END_REG_TIME = "reg_time.keyword";//注册时间
    public static final String STATUS_KEY = "status_key.keyword";//商户状态
    public static final String TRADE_BUY = "trade_buy.is_has_order";//是否下过单--true  false
    public static final String IS_ALLOW_CONTACT_LENS = "is_allow_contact_lens.keyword";//是否允许采销医疗器械--buy sell
    public static final String IS_ALLOW_CONTACT_LEN = "is_allow_contact_lens";//是否允许采销医疗器械--buy sell
    public static final String BUSINESS_SCOPE = "business_scope.keyword";

    //统计数量
    public static final String ENTITY_NAME = "entity.entity_name.keyword";//企业名称

    public static final String ACCOUNT_NAME = "account.user_id.keyword";//账号数量

    public static final String SHOP_ID = "id";//店铺数量

    public static final String SELL_ORDER_PRICE_TOTAL = "trade_sell.order_price_total";//供货营业额

    public static final String SELL_ORDER_COUNT = "trade_sell.order_count";//供货订单量

    public static final String BUY_ORDER_PRICE_TOTAL = "trade_buy.order_price_total";//采购营业额

    public static final String BUY_ORDER_COUNT = "trade_buy.order_count";//采购订单量

    //医疗状态
    public static final String MEDICAL_DEVICE_TYPE  = "business_license_of_medical_device.business_type";

    public ElasticMerchantKey() {
    }
}

