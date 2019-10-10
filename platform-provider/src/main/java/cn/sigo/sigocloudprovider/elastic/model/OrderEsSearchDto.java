package cn.sigo.sigocloudprovider.elastic.model;

/**
 * @Auther: lxj
 * @Date: 2019/8/22 21:00
 * @Description:
 */
public class OrderEsSearchDto {
    //订单号
    private String orderCode;

    //供货商
    private String sellerAccount;
    //采购商
    private String buyerAccount;
    //省
    private String province;
    //市
    private String city;
    //区
    private String area;
    //订单状态
    private String orderStatus;
    //发货状态
    private String subOrderStatus;
    //关闭状态
    private String closeType;
    //售后状态
    private String afterSaleStatus;
    //付款用户
    private String payUser;
    //区域经理
    private String managerName;
    //下单时间
    private String startDate;
    private String endDate;
    //付款时间
    private String refundCode;//退款单号

    private String orderState;//订单状态

    private Integer participle;//是否分词

//------------------------------------------------------------
    private String order_code;
    private String[]  order_state_key;
    private String create_time_begin;
    private String create_time_end;
    private String pay_time_begin;
    private String pay_time_end;
    private String seller_shop_name;
    private String buyer_shop_name;
    private String[] buyer_shop_area_province;
    private String[] buyer_shop_area_city;
    private String product_name;
    private Integer page_index = 1;
    private Integer page_size = 20;

    public Integer getParticiple() {
        return participle;
    }

    public void setParticiple(Integer participle) {
        this.participle = participle;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String[] getOrder_state_key() {
        return order_state_key;
    }

    public void setOrder_state_key(String[] order_state_key) {
        this.order_state_key = order_state_key;
    }

    public String getCreate_time_begin() {
        return create_time_begin;
    }

    public void setCreate_time_begin(String create_time_begin) {
        this.create_time_begin = create_time_begin;
    }

    public String getCreate_time_end() {
        return create_time_end;
    }

    public void setCreate_time_end(String create_time_end) {
        this.create_time_end = create_time_end;
    }

    public String getPay_time_begin() {
        return pay_time_begin;
    }

    public void setPay_time_begin(String pay_time_begin) {
        this.pay_time_begin = pay_time_begin;
    }

    public String getPay_time_end() {
        return pay_time_end;
    }

    public void setPay_time_end(String pay_time_end) {
        this.pay_time_end = pay_time_end;
    }

    public String getSeller_shop_name() {
        return seller_shop_name;
    }

    public void setSeller_shop_name(String seller_shop_name) {
        this.seller_shop_name = seller_shop_name;
    }

    public String getBuyer_shop_name() {
        return buyer_shop_name;
    }

    public void setBuyer_shop_name(String buyer_shop_name) {
        this.buyer_shop_name = buyer_shop_name;
    }

    public String[] getBuyer_shop_area_province() {
        return buyer_shop_area_province;
    }

    public void setBuyer_shop_area_province(String[] buyer_shop_area_province) {
        this.buyer_shop_area_province = buyer_shop_area_province;
    }

    public String[] getBuyer_shop_area_city() {
        return buyer_shop_area_city;
    }

    public void setBuyer_shop_area_city(String[] buyer_shop_area_city) {
        this.buyer_shop_area_city = buyer_shop_area_city;
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

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * 页码
     */
    private Integer pageindex = 1;
    /**
     * 页宽
     */
    private Integer pagesize = 5;

    public Integer getPageindex() {
        return pageindex;
    }

    public void setPageindex(Integer pageindex) {
        this.pageindex = pageindex;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(String subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public String getCloseType() {
        return closeType;
    }

    public void setCloseType(String closeType) {
        this.closeType = closeType;
    }

    public String getAfterSaleStatus() {
        return afterSaleStatus;
    }

    public void setAfterSaleStatus(String afterSaleStatus) {
        this.afterSaleStatus = afterSaleStatus;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
