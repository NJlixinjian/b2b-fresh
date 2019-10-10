package cn.sigo.sigocloudprovider.model;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 18:04
 * @Description:
 */
public class Product {

    private String productCode;

    private Long productId;

    private Long sellerCompanyId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerCompanyId() {
        return sellerCompanyId;
    }

    public void setSellerCompanyId(Long sellerCompanyId) {
        this.sellerCompanyId = sellerCompanyId;
    }
}
