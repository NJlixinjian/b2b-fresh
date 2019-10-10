package cn.sigo.sigocloudprovider.service;

import cn.sigo.sigocloudprovider.model.vo.CompanyInfoVo;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 15:56
 * @Description:
 */
public interface CompanyService {

    /**
     * 开启/关闭账号
     *
     * @param companyInfo
     */
    void updateAccount(CompanyInfoVo companyInfo);
}
