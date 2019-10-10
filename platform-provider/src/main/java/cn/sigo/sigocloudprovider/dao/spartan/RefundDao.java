package cn.sigo.sigocloudprovider.dao.spartan;

import cn.sigo.sigocloudprovider.model.Refund;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 17:10
 * @Description:
 */
public interface RefundDao {

    Refund queryRefundInfo(@Param("orderCode") String orderCode);

    /**
     *
     * 根据订单号 统计 退款中的 退款信息
     *
     * @param orderCode
     * @return
     */
    Integer countRefundingByOrderCode(String orderCode);


}
