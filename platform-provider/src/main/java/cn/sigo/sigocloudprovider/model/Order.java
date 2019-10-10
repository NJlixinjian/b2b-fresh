package cn.sigo.sigocloudprovider.model;

import java.util.Date;
import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 14:28
 * @Description:
 */
public class Order {

    private Long orderId;//订单id

    private String orderCode;//订单code

    private String status;//订单状态

    private Long buyerId;

    private String buyerName;

    private Long sellerId;

    private String sellerName;

    private String consigneeName;//收货人姓名

    private String consigneePhone;//收货人手机

    private String province;

    private String city;

    private String district;//区县

    private String street;//乡镇街道

    private String addressDetail;//详细地址

    private String expectedDeliveryTime;//期望送货时间

    private String payChannel;//支付渠道

    private String payType;//支付渠道

    private String payCode;//支付流水号

    private String cod;//是否货到付款: 0否 1是

    private String orderPrice;//订单金额

    private String postage;

    private String totalCost;//订单总成本

    private String actualIncome;//实收金额(确认收货后，金额等于实付金额)

    private String actualPrice;//实际应收金额

    private String actualPay;//实付金额

    private String promoteDiscount;//被动促销折扣 (系统级的打折如活动)

    private String activeDiscount;//主动折扣金额(优惠券 积分)

    private String manualDiscount;//手工折扣金额  (商家人工改价)

    private String orderSource;//下单来源

    private String orderType;//订单类型 普通normal 预售presale 铺货distribute 代销consign

    private String urgeLevel;//催促等级: 0默认

    private String buyerRemark;

    private String sellerRemark;

    private String exceptionDescription;//订单异常描述

    private Date orderTime;//下单时间

    private Date payTime;//付款时间

    private Date sendTime;//发货时间

    private Date lastTime;//订单最后更新时间

    private Date cancelTime;//取消时间

    private Date closeTime;//关闭时间

    private Date timeoutTime;//超时过期时间

    private Date confirmTime;//确认收货时间

    private Long groupId;//订单分组id

    private String payStatus;//支付状态

    private String deliveryStatus;//配送状态

    private String orderDevice;//下单设备

    private String orderCreateIp;//下单用户ip

    private String orderVersion;//订单版本信息

    private String shippingListWay;//发货清单发送方式---随货同行: follow ,一件代发: alone

    private String remark;//平台备注

    private String cancelCause;//取消原因

    private String payAccount;//付款账号

    private String sellerFlag;//卖家旗帜

    private String linePayment;//线下转账操作人

    private String payDeviceIp;//支付设备ip

    private String invoiceStatus;//开票状态

    private String linePaymentTime;

    private String orderDeviceIp;

    private String hippingListWay;

    private Long  buyerAccountId;

    private List<OrderDetail> orderDetailList;//订单详情

    private String orderStatus;//节点状态

    private List<List<OrderDetail>> packageList;//包裹

    private CompanyInfo buyerInfo;//采购商信息

    private CompanyInfo sellerInfo;//供货商信息

    private String additional;//附加状态

    private String refundCode;//退款单号

    private Long refundId;//退款id

    private String payer;//付款方

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public CompanyInfo getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(CompanyInfo buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    public CompanyInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(CompanyInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public List<List<OrderDetail>> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<List<OrderDetail>> packageList) {
        this.packageList = packageList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getLinePaymentTime() {
        return linePaymentTime;
    }

    public void setLinePaymentTime(String linePaymentTime) {
        this.linePaymentTime = linePaymentTime;
    }

    public String getOrderDeviceIp() {
        return orderDeviceIp;
    }

    public void setOrderDeviceIp(String orderDeviceIp) {
        this.orderDeviceIp = orderDeviceIp;
    }

    public String getHippingListWay() {
        return hippingListWay;
    }

    public void setHippingListWay(String hippingListWay) {
        this.hippingListWay = hippingListWay;
    }

    public Long getBuyerAccountId() {
        return buyerAccountId;
    }

    public void setBuyerAccountId(Long buyerAccountId) {
        this.buyerAccountId = buyerAccountId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(String actualIncome) {
        this.actualIncome = actualIncome;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getActualPay() {
        return actualPay;
    }

    public void setActualPay(String actualPay) {
        this.actualPay = actualPay;
    }

    public String getPromoteDiscount() {
        return promoteDiscount;
    }

    public void setPromoteDiscount(String promoteDiscount) {
        this.promoteDiscount = promoteDiscount;
    }

    public String getActiveDiscount() {
        return activeDiscount;
    }

    public void setActiveDiscount(String activeDiscount) {
        this.activeDiscount = activeDiscount;
    }

    public String getManualDiscount() {
        return manualDiscount;
    }

    public void setManualDiscount(String manualDiscount) {
        this.manualDiscount = manualDiscount;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getUrgeLevel() {
        return urgeLevel;
    }

    public void setUrgeLevel(String urgeLevel) {
        this.urgeLevel = urgeLevel;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getTimeoutTime() {
        return timeoutTime;
    }

    public void setTimeoutTime(Date timeoutTime) {
        this.timeoutTime = timeoutTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderDevice() {
        return orderDevice;
    }

    public void setOrderDevice(String orderDevice) {
        this.orderDevice = orderDevice;
    }

    public String getOrderCreateIp() {
        return orderCreateIp;
    }

    public void setOrderCreateIp(String orderCreateIp) {
        this.orderCreateIp = orderCreateIp;
    }

    public String getOrderVersion() {
        return orderVersion;
    }

    public void setOrderVersion(String orderVersion) {
        this.orderVersion = orderVersion;
    }

    public String getShippingListWay() {
        return shippingListWay;
    }

    public void setShippingListWay(String shippingListWay) {
        this.shippingListWay = shippingListWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getSellerFlag() {
        return sellerFlag;
    }

    public void setSellerFlag(String sellerFlag) {
        this.sellerFlag = sellerFlag;
    }

    public String getLinePayment() {
        return linePayment;
    }

    public void setLinePayment(String linePayment) {
        this.linePayment = linePayment;
    }

    public String getPayDeviceIp() {
        return payDeviceIp;
    }

    public void setPayDeviceIp(String payDeviceIp) {
        this.payDeviceIp = payDeviceIp;
    }
}
