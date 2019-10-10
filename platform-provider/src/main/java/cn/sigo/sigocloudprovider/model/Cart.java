package cn.sigo.sigocloudprovider.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xudou
 * @date 2019/06/28
 */
public class Cart {
    private Long cartId;

    private Long accountId;

    private Long companyId;

    private String companyName;

    private Long skuId;

    private String skuCode;

    private Long productId;

    private String productCode;

    private String productName;

    private BigDecimal price;

    private Integer count;

    private Long sellerAccountId;

    private Long sellerCompanyId;

    private String sellerCompanyName;

    private String appCode;

    private Boolean isCustom;

    private String skuEye;

    private String skuSph;

    private String skuCyl;

    private String skuAxis;

    private String skuAdd;

    private String skuSix;

    private Boolean disabled;

    private Date versionNumber;

    private String adder;

    private Date addTime;

    private String moder;

    private Date modTime;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getSellerAccountId() {
        return sellerAccountId;
    }

    public void setSellerAccountId(Long sellerAccountId) {
        this.sellerAccountId = sellerAccountId;
    }

    public Long getSellerCompanyId() {
        return sellerCompanyId;
    }

    public void setSellerCompanyId(Long sellerCompanyId) {
        this.sellerCompanyId = sellerCompanyId;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName == null ? null : sellerCompanyName.trim();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

    public String getSkuEye() {
        return skuEye;
    }

    public void setSkuEye(String skuEye) {
        this.skuEye = skuEye == null ? null : skuEye.trim();
    }

    public String getSkuSph() {
        return skuSph;
    }

    public void setSkuSph(String skuSph) {
        this.skuSph = skuSph == null ? null : skuSph.trim();
    }

    public String getSkuCyl() {
        return skuCyl;
    }

    public void setSkuCyl(String skuCyl) {
        this.skuCyl = skuCyl == null ? null : skuCyl.trim();
    }

    public String getSkuAxis() {
        return skuAxis;
    }

    public void setSkuAxis(String skuAxis) {
        this.skuAxis = skuAxis == null ? null : skuAxis.trim();
    }

    public String getSkuAdd() {
        return skuAdd;
    }

    public void setSkuAdd(String skuAdd) {
        this.skuAdd = skuAdd == null ? null : skuAdd.trim();
    }

    public String getSkuSix() {
        return skuSix;
    }

    public void setSkuSix(String skuSix) {
        this.skuSix = skuSix == null ? null : skuSix.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Date getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Date versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getAdder() {
        return adder;
    }

    public void setAdder(String adder) {
        this.adder = adder == null ? null : adder.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getModer() {
        return moder;
    }

    public void setModer(String moder) {
        this.moder = moder == null ? null : moder.trim();
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}