package cn.sigo.sigocloudprovider.elastic.model;


public class ResponseShop {
    private Long shop_id;

    private String shop_name;

    private ResponseShopArea shop_area;

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public ResponseShopArea getShop_area() {
        return shop_area;
    }

    public void setShop_area(ResponseShopArea shop_area) {
        this.shop_area = shop_area;
    }
}
