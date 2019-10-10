package cn.sigo.sigocloudprovider.service.spartan.impl;

import cn.sigo.sigocloudprovider.dao.spartan.RefundDao;
import cn.sigo.sigocloudprovider.model.Refund;
import cn.sigo.sigocloudprovider.service.spartan.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 17:13
 * @Description:
 */
@Service
public class RefundServiceImpl implements RefundService{

    @Autowired
    private RefundDao refundDao;

    @Override
    public Refund queryRefundInfo(String orderCode) {
        return refundDao.queryRefundInfo(orderCode);
    }
}
