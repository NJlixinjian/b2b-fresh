package cn.sigo.sigocloudprovider.elastic.model.MerchantEs;

import java.util.List;

public class PushMerchantToEs {
    private String shop_id;

    private String shop_name;

    private ShopArea shop_area;

    private String shop_type;

    private List<String> business_scope;

    private List<String> is_allow_contact_lens;

    private String sale_mode;

    private String sale_mode_key;

    private String reg_time;

    private Entiterprise entity;

    private Contact contact;

    private Account account;

    private String area_manager;

    private TradeSell trade_sell;

    private TradeBuy trade_buy;

    private String status;

    private String status_key;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public ShopArea getShop_area() {
        return shop_area;
    }

    public void setShop_area(ShopArea shop_area) {
        this.shop_area = shop_area;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public List<String> getBusiness_scope() {
        return business_scope;
    }

    public void setBusiness_scope(List<String> business_scope) {
        this.business_scope = business_scope;
    }

    public List<String> getIs_allow_contact_lens() {
        return is_allow_contact_lens;
    }

    public void setIs_allow_contact_lens(List<String> is_allow_contact_lens) {
        this.is_allow_contact_lens = is_allow_contact_lens;
    }

    public String getSale_mode() {
        return sale_mode;
    }

    public void setSale_mode(String sale_mode) {
        this.sale_mode = sale_mode;
    }

    public String getSale_mode_key() {
        return sale_mode_key;
    }

    public void setSale_mode_key(String sale_mode_key) {
        this.sale_mode_key = sale_mode_key;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public Entiterprise getEntity() {
        return entity;
    }

    public void setEntity(Entiterprise entity) {
        this.entity = entity;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getArea_manager() {
        return area_manager;
    }

    public void setArea_manager(String area_manager) {
        this.area_manager = area_manager;
    }

    public TradeSell getTrade_sell() {
        return trade_sell;
    }

    public void setTrade_sell(TradeSell trade_sell) {
        this.trade_sell = trade_sell;
    }

    public TradeBuy getTrade_buy() {
        return trade_buy;
    }

    public void setTrade_buy(TradeBuy trade_buy) {
        this.trade_buy = trade_buy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_key() {
        return status_key;
    }

    public void setStatus_key(String status_key) {
        this.status_key = status_key;
    }
}
