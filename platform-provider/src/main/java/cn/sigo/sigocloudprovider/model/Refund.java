package cn.sigo.sigocloudprovider.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 17:11
 * @Description:
 */
public class Refund {
    private Long refundId;

    private Long orderId;

    private Long orderCode;

    private String refundCode;

    private String sellerCompanyName;

    private String buyerCompanyName;

    private Long sellerCompanyId;

    private Long buyerCompanyId;

    private String buyerAccount;

    private String buyerPhone;

    private Byte refundType;

    private BigDecimal payMoney;

    private BigDecimal refundMoney;

    private String refundReson;

    private String refundDescription;

    private Integer refundStatus;

    private Byte commentFlag;

    private String createUser;

    private String modifyUser;

    private Date modifyTime;

    private Date createTime;

    private Byte receiveFlag;

    private Date versionNumber;

    private Date waitStartTime;

    private String orderStatus;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public String getBuyerCompanyName() {
        return buyerCompanyName;
    }

    public void setBuyerCompanyName(String buyerCompanyName) {
        this.buyerCompanyName = buyerCompanyName;
    }

    public Long getSellerCompanyId() {
        return sellerCompanyId;
    }

    public void setSellerCompanyId(Long sellerCompanyId) {
        this.sellerCompanyId = sellerCompanyId;
    }

    public Long getBuyerCompanyId() {
        return buyerCompanyId;
    }

    public void setBuyerCompanyId(Long buyerCompanyId) {
        this.buyerCompanyId = buyerCompanyId;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Byte getRefundType() {
        return refundType;
    }

    public void setRefundType(Byte refundType) {
        this.refundType = refundType;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRefundReson() {
        return refundReson;
    }

    public void setRefundReson(String refundReson) {
        this.refundReson = refundReson;
    }

    public String getRefundDescription() {
        return refundDescription;
    }

    public void setRefundDescription(String refundDescription) {
        this.refundDescription = refundDescription;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Byte getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(Byte commentFlag) {
        this.commentFlag = commentFlag;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getReceiveFlag() {
        return receiveFlag;
    }

    public void setReceiveFlag(Byte receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    public Date getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Date versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getWaitStartTime() {
        return waitStartTime;
    }

    public void setWaitStartTime(Date waitStartTime) {
        this.waitStartTime = waitStartTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
