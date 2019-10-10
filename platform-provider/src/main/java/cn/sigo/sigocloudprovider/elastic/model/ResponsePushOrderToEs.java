package cn.sigo.sigocloudprovider.elastic.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResponsePushOrderToEs  implements Serializable {
    private String order_code;

    private String order_state;

    private String app_type;

    private String trade_type;

    private Date create_time;

    private Date pay_time;

    private Date last_mod_time;

    private Double order_price;

    private Double actual_price;

    private Double actual_pay;

    private String pay_type;

    private String consignee;

    private String phone;

    private String province;

    private String city;

    private String district;

    private String address_detail;

    private Double gross_profit;

    private Double gross_profit_rate;

    private ResponseShop seller_shop;

    private ResponseShop buyer_shop;

    private List<Sku> sku;

    public Double getActual_price() {
        return actual_price;
    }

    public void setActual_price(Double actual_price) {
        this.actual_price = actual_price;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getLast_mod_time() {
        return last_mod_time;
    }

    public void setLast_mod_time(Date last_mod_time) {
        this.last_mod_time = last_mod_time;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public Double getActual_pay() {
        return actual_pay;
    }

    public void setActual_pay(Double actual_pay) {
        this.actual_pay = actual_pay;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public Double getGross_profit() {
        return gross_profit;
    }

    public void setGross_profit(Double gross_profit) {
        this.gross_profit = gross_profit;
    }

    public Double getGross_profit_rate() {
        return gross_profit_rate;
    }

    public void setGross_profit_rate(Double gross_profit_rate) {
        this.gross_profit_rate = gross_profit_rate;
    }

    public ResponseShop getSeller_shop() {
        return seller_shop;
    }

    public void setSeller_shop(ResponseShop seller_shop) {
        this.seller_shop = seller_shop;
    }

    public ResponseShop getBuyer_shop() {
        return buyer_shop;
    }

    public void setBuyer_shop(ResponseShop buyer_shop) {
        this.buyer_shop = buyer_shop;
    }

    public List<Sku> getSku() {
        return sku;
    }

    public void setSku(List<Sku> sku) {
        this.sku = sku;
    }
}
