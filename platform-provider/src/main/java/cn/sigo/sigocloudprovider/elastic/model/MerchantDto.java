package cn.sigo.sigocloudprovider.elastic.model;

import java.util.Date;

/**
 * @Auther: lxj
 * @Date: 2019/9/23 20:52
 * @Description:
 */
public class MerchantDto {

    private String shop_name;//商铺名称

    private String area_manager;//区域经理

    private String[] provice_code;//省编码

    private String[] city_code;//市编码

    private Date reg_time;//申请时间

    private String begin_reg_time;//开始

    private String end_reg_time;//结束

    private String[] medical_status;//医疗许可证审核状态

    private String[] status_key;//商户状态

    private String[] is_allow_contact_lens;//是否允许采销医疗器械

    private Integer price_sort;//金额排序 1、2

    private Integer order_sort;//订单量排序 1、2

    private Integer buy_order_count;//采购单量排序1-正；2-倒

    private Integer sell_order_count;//供货单量排序

    private Integer buy_order_price_total;//采购金额排序1-正；2-倒

    private Integer sell_order_price_total;//供货金额排序1-正；2-倒

    private String business_scope;//业务范围

    private String contact_info;//联系信息

    private String company_info;//企业信息

    private String login_info;//登录账号

    private Integer page_index = 0;

    private Integer page_size = 20;

    public Integer getBuy_order_price_total() {
        return buy_order_price_total;
    }

    public void setBuy_order_price_total(Integer buy_order_price_total) {
        this.buy_order_price_total = buy_order_price_total;
    }

    public Integer getSell_order_price_total() {
        return sell_order_price_total;
    }

    public void setSell_order_price_total(Integer sell_order_price_total) {
        this.sell_order_price_total = sell_order_price_total;
    }

    public Integer getBuy_order_count() {
        return buy_order_count;
    }

    public void setBuy_order_count(Integer buy_order_count) {
        this.buy_order_count = buy_order_count;
    }

    public Integer getSell_order_count() {
        return sell_order_count;
    }

    public void setSell_order_count(Integer sell_order_count) {
        this.sell_order_count = sell_order_count;
    }

    public String getLogin_info() {
        return login_info;
    }

    public void setLogin_info(String login_info) {
        this.login_info = login_info;
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getBusiness_scope() {
        return business_scope;
    }

    public void setBusiness_scope(String business_scope) {
        this.business_scope = business_scope;
    }

    public Integer getPrice_sort() {
        return price_sort;
    }

    public void setPrice_sort(Integer price_sort) {
        this.price_sort = price_sort;
    }

    public Integer getOrder_sort() {
        return order_sort;
    }

    public void setOrder_sort(Integer order_sort) {
        this.order_sort = order_sort;
    }

    public String[] getIs_allow_contact_lens() {
        return is_allow_contact_lens;
    }

    public void setIs_allow_contact_lens(String[] is_allow_contact_lens) {
        this.is_allow_contact_lens = is_allow_contact_lens;
    }

    public String[] getStatus_key() {
        return status_key;
    }

    public void setStatus_key(String[] status_key) {
        this.status_key = status_key;
    }

    public String[] getMedical_status() {
        return medical_status;
    }

    public void setMedical_status(String[] medical_status) {
        this.medical_status = medical_status;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public String getBegin_reg_time() {
        return begin_reg_time;
    }

    public void setBegin_reg_time(String begin_reg_time) {
        this.begin_reg_time = begin_reg_time;
    }

    public String getEnd_reg_time() {
        return end_reg_time;
    }

    public void setEnd_reg_time(String end_reg_time) {
        this.end_reg_time = end_reg_time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }


    public String getArea_manager() {
        return area_manager;
    }

    public void setArea_manager(String area_manager) {
        this.area_manager = area_manager;
    }

    public String[] getProvice_code() {
        return provice_code;
    }

    public void setProvice_code(String[] provice_code) {
        this.provice_code = provice_code;
    }

    public String[] getCity_code() {
        return city_code;
    }

    public void setCity_code(String[] city_code) {
        this.city_code = city_code;
    }

    public Date getReg_time() {
        return reg_time;
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
    }
}
