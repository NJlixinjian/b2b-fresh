package cn.sigo.sigocloudprovider.elastic.service;

/**
 * @Auther: lxj
 * @Date: 2019/8/23 10:21
 * @Description:
 */
public class ElasticOrderKey {
    public static final String CLUSTER_NAME = "cluster.name";
    public static final String DELETE_SUCCEED = "OK";
    public static final String ORDER_INDEX = "index_order";//索引
    public static final String ORDER_TYPE = "_doc";//索引类型
    public static final String ORDER_CODE = "order_code";//订单号
    public static final String BUYER_ACCOUNT = "buyer_shop.shop_name.keyword";//采购商
    public static final String SELLER_ACCOUNT = "seller_shop.shop_name.keyword";//供货商
    public static final String BUYER_PROVINCE = "buyer_shop.shop_area.province.code";//省
    public static final String BUYER_CITY = "buyer_shop.shop_area.city.code"; //市
    public static final String BUYER_AREA = "buyer_shop.shop_city.area.name";//区
    public static final String ORDER_STATE_KEY = "order_state_key";//订单状态
    public static final String CREATE_TIME_BEGIN = "create_time";
    public static final String CREATE_TIME_END = "create_time";
    public static final String PAY_TIME_BEGIN = "pay_time";
    public static final String PAY_TIME_END = "pay_time";
    public static final String PRODUCT_NAME = "sku.product_name";//产品名称
    public static final String ORDER_PRICE = "actual_price";//订单金额
    public static final String ACTUAL_PAY = "actual_pay";//实际支付金额
    public static final String TOTAL_AMOUNT = "refund.total_amount";//退款金额
    public static final String BUYER_SHOP_ID = "buyer_shop.shop_id";//采购商家id

    public static final String PAY_USER = "payUser";//付款用户
    public static final String MANAGER_NAME = "managerName"; //区域经理
    public ElasticOrderKey() {
    }
}
