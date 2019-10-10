package cn.sigo.sigocloudprovider.dao.order;


import cn.sigo.sigocloudprovider.model.Order;
import cn.sigo.sigocloudprovider.model.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 15:53
 * @Description:
 */
public interface OrderDao {

    Order queryOrderDetail(@Param("orderCode") String orderCode);

    List<OrderDetail> queryProductDetail(@Param("orderCode") String orderCode);

    List<OrderDetail> queryInfoByExpressNum(@Param("orderCode") String orderCode,@Param("expressNum") String expressNum);

    OrderDetail querySendSome(@Param("orderCode") String orderCode);
}
