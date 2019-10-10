package cn.sigo.sigocloudprovider.model.vo;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 16:26
 * @Description:
 */
public class CompanyInfoVo {

    private Long companyId;

    private Byte checkStatus;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }
}
