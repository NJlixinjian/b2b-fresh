package cn.sigo.sigocloudprovider.model.vo;


/**
 * @Auther: lxj
 * @Date: 2019/9/10 11:34
 * @Description:
 */
public class TransferAccountVo {
    private String orderCode;
    private String channel;
    private String type;
    private double price;
    private String payCode;
    private String payAccount;
    private String linePayment;
    private String accountId;
    private String accountName;
    private Long sellerId;
    private String remark;
    private long payTime;

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    private String payer;//付款人

    private String payerBank;//付款银行

    private String payerAccount;//付款账号

    private String receivables;//收款人

    private String receivablesBank;//收款银行

    private String receivablesAccount;//收款银行账号

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayerBank() {
        return payerBank;
    }

    public void setPayerBank(String payerBank) {
        this.payerBank = payerBank;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public String getReceivables() {
        return receivables;
    }

    public void setReceivables(String receivables) {
        this.receivables = receivables;
    }

    public String getReceivablesBank() {
        return receivablesBank;
    }

    public void setReceivablesBank(String receivablesBank) {
        this.receivablesBank = receivablesBank;
    }

    public String getReceivablesAccount() {
        return receivablesAccount;
    }

    public void setReceivablesAccount(String receivablesAccount) {
        this.receivablesAccount = receivablesAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getLinePayment() {
        return linePayment;
    }

    public void setLinePayment(String linePayment) {
        this.linePayment = linePayment;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
