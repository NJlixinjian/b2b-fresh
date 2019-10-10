package cn.sigo.sigocloudprovider.controller;

import cn.sigo.sigocloudprovider.config.CommonDto;
import cn.sigo.sigocloudprovider.model.Order;
import cn.sigo.sigocloudprovider.model.vo.TransferAccountVo;
import cn.sigo.sigocloudprovider.service.order.OrderService;
import cn.sigo.yjq.domainorderproviderapi.model.bo.RequestMainOrder;
import cn.sigo.yjq.domainorderproviderapi.service.OrderFeignApi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 15:58
 * @Description:
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFeignApi orderFeignApi;



    /**
     * 查询订单详情
     *
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "order/queryorderdetail", method = RequestMethod.GET)
    public Order queryOrderDetail(String orderCode){
        Order order = orderService.queryOrderDetail(orderCode);
        return order;
    }

    /**
     * 线下转账
     *
     * @param transferAccountVo
     */
    @RequestMapping(value = "order/transferaccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public CommonDto TransferAccount(@RequestBody TransferAccountVo transferAccountVo, HttpServletRequest request){
        CommonDto commonDto = orderService.TransferAccount(transferAccountVo,request);
        if (commonDto.getResult() == true) {
            return new CommonDto(true);
        } else {
            return new CommonDto(false);
        }
    }


    /**
     * 平台备注
     *
     * @param transferAccountVo
     * @return
     */
    @RequestMapping(value = "order/updateremark", method = RequestMethod.POST)
    public CommonDto updateRemark(@RequestBody TransferAccountVo transferAccountVo){
        RequestMainOrder requestMainOrder = new RequestMainOrder();
        try {
            requestMainOrder.setOrder_code(transferAccountVo.getOrderCode());
            if (StringUtils.isNotBlank(transferAccountVo.getRemark())) {
                requestMainOrder.setRemark(transferAccountVo.getRemark());
            } else {
                requestMainOrder.setRemark("null");
            }
            orderFeignApi.updateOrder(requestMainOrder);
        } catch (Exception e) {
            return new CommonDto(false);
        }
        return new CommonDto(true);
    }
}
