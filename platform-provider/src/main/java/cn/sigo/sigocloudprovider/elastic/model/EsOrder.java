package cn.sigo.sigocloudprovider.elastic.model;

import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/8/23 18:40
 * @Description:
 */
public class EsOrder {
    private String order_code;
    private String order_state;
    private String app_type;
    private String trade_type;
    private String create_time;
    private String pay_time;
    private String last_mod_time;
    private String order_price;
    private String actual_pay;
    private String pay_type;
    private String consignee;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String address_detail;
    private String gross_profit;
    private String gross_profit_rate;
    private List<seller_shop> seller_shops;
}

class seller_shop{
    private String shop_id;
    private String shop_name;

}
