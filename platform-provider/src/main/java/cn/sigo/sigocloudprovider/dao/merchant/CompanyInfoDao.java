package cn.sigo.sigocloudprovider.dao.merchant;

import cn.sigo.sigocloudprovider.model.CompanyInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: lxj
 * @Date: 2019/9/10 11:22
 * @Description:
 */
public interface CompanyInfoDao {

    /**
     * 查询商家信息
     * @param companyId
     * @return
     */
    CompanyInfo queryCompanyInfo(@Param("companyId") Long companyId);

    void updateAccount(CompanyInfo companyInfo);

}
