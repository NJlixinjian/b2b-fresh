package cn.sigo.sigocloudprovider.service.impl;

import cn.sigo.sigocloudprovider.dao.merchant.CompanyInfoDao;
import cn.sigo.sigocloudprovider.model.CompanyInfo;
import cn.sigo.sigocloudprovider.model.vo.CompanyInfoVo;
import cn.sigo.sigocloudprovider.service.CompanyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 15:57
 * @Description:
 */
@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyInfoDao companyInfoDao;

    @Override
    public void updateAccount(CompanyInfoVo companyInfoVo) {

        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(companyInfoVo.getCompanyId());
        companyInfoDao.updateAccount(companyInfo);
    }
}
