package cn.sigo.sigocloudprovider.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: lxj
 * @Date: 2019/7/10 11:12
 * @Description:
 */
public class OrderDetail {

    private Long itemId;//商品id

    private Long sku;//skuId

    private String itemCode;//商品编码

    private String itemName;//商品名称

    private String itemImg;//商品图片路径

    private String itemCategory;//类目

    private String skuData;//规格信息(商品属性)

    private Integer number;//数量

    private BigDecimal unitPrice;//单价(交易单价)

    private BigDecimal actualPrice;//小计

    private String expressNum;//快递单号

    private String status;

    private String expressCompany;

    private BigDecimal subtotal;

    private String detailStatus;//详情订单

    private Date detailSendTime;//子订单发货时间

    public Date getDetailSendTime() {
        return detailSendTime;
    }

    public void setDetailSendTime(Date detailSendTime) {
        this.detailSendTime = detailSendTime;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getSkuData() {
        return skuData;
    }

    public void setSkuData(String skuData) {
        this.skuData = skuData;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }
}
