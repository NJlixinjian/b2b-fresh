package cn.sigo.sigocloudprovider.elastic.model.MerchantEs;

/**
 * @Auther: lxj
 * @Date: 2019/9/24 18:52
 * @Description:
 */
public class MerchantStatistics {

    private double shopNum;

    private double enterpriseNum;

    private double accountNum;

    private double submitNum;//审核通过

    private double waitAuditNum;//待审核

    private double refusedNum;//审核拒绝

    public double getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(double submitNum) {
        this.submitNum = submitNum;
    }

    public double getWaitAuditNum() {
        return waitAuditNum;
    }

    public void setWaitAuditNum(double waitAuditNum) {
        this.waitAuditNum = waitAuditNum;
    }

    public double getRefusedNum() {
        return refusedNum;
    }

    public void setRefusedNum(double refusedNum) {
        this.refusedNum = refusedNum;
    }

    public double getShopNum() {
        return shopNum;
    }

    public void setShopNum(double shopNum) {
        this.shopNum = shopNum;
    }

    public double getEnterpriseNum() {
        return enterpriseNum;
    }

    public void setEnterpriseNum(double enterpriseNum) {
        this.enterpriseNum = enterpriseNum;
    }

    public double getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(double accountNum) {
        this.accountNum = accountNum;
    }
}
