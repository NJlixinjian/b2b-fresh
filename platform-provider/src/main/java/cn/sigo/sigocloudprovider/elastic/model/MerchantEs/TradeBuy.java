package cn.sigo.sigocloudprovider.elastic.model.MerchantEs;


public class TradeBuy {
    private Boolean is_has_order;

    private Double order_price_total;

    private Integer order_count;

    private Double order_price_avg;

    private String order_recent_create_time;

    public Boolean getIs_has_order() {
        return is_has_order;
    }

    public void setIs_has_order(Boolean is_has_order) {
        this.is_has_order = is_has_order;
    }

    public Double getOrder_price_total() {
        return order_price_total;
    }

    public void setOrder_price_total(Double order_price_total) {
        this.order_price_total = order_price_total;
    }

    public Integer getOrder_count() {
        return order_count;
    }

    public void setOrder_count(Integer order_count) {
        this.order_count = order_count;
    }

    public Double getOrder_price_avg() {
        return order_price_avg;
    }

    public void setOrder_price_avg(Double order_price_avg) {
        this.order_price_avg = order_price_avg;
    }

    public String getOrder_recent_create_time() {
        return order_recent_create_time;
    }

    public void setOrder_recent_create_time(String order_recent_create_time) {
        this.order_recent_create_time = order_recent_create_time;
    }
}
