package cn.sigo.sigocloudprovider.service.spartan;

import cn.sigo.sigocloudprovider.model.Refund;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 17:12
 * @Description:
 */
public interface RefundService {

    Refund queryRefundInfo(String orderCode);
}
