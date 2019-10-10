package cn.sigo.sigocloudprovider.service.order;

import cn.sigo.sigocloudprovider.config.CommonDto;
import cn.sigo.sigocloudprovider.model.Order;
import cn.sigo.sigocloudprovider.model.vo.TransferAccountVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 15:51
 * @Description:
 */
public interface OrderService{

    //查询订单详情
    Order queryOrderDetail(String orderCode);

    //线下转账
    CommonDto TransferAccount(TransferAccountVo transferAccountVo,HttpServletRequest request);

}
