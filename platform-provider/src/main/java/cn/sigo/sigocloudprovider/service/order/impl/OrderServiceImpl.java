package cn.sigo.sigocloudprovider.service.order.impl;

import cn.sigo.sigocloudprovider.config.CommonDto;
import cn.sigo.sigocloudprovider.dao.merchant.CompanyInfoDao;
import cn.sigo.sigocloudprovider.dao.order.OrderDao;
import cn.sigo.sigocloudprovider.dao.spartan.RefundDao;
import cn.sigo.sigocloudprovider.model.*;
import cn.sigo.sigocloudprovider.model.vo.TransferAccountVo;
import cn.sigo.sigocloudprovider.service.SmsService;
import cn.sigo.sigocloudprovider.service.order.OrderService;
import cn.sigo.sigocloudprovider.util.IpUtil;
import cn.sigo.yjq.domainorderproviderapi.model.bo.RequestMainOrder;
import cn.sigo.yjq.domainorderproviderapi.model.bo.RequestPay;
import cn.sigo.yjq.domainorderproviderapi.service.OrderFeignApi;
import cn.sigo.yjq.payproviderapi.model.bo.RequestTransferInitPay;
import cn.sigo.yjq.payproviderapi.model.bo.RequestUpdateFundOrder;
import cn.sigo.yjq.payproviderapi.service.payment.PayFeignApi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 15:53
 * @Description:
 */

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RefundDao refundDao;

    @Autowired
    private CompanyInfoDao companyInfoDao;

    @Autowired
    private SmsService smsService;

    @Autowired
    private PayFeignApi payFeignApi;

    @Autowired
    private OrderFeignApi orderFeignApi;
    /**
     * 查询订单详情
     *
     * @param orderCode
     * @return
     */
    @Override
    public Order queryOrderDetail(String orderCode) {
        List<String> list = new ArrayList<>();
        List<List<OrderDetail>> detailList = new ArrayList<>();
        Order order = orderDao.queryOrderDetail(orderCode);
        List<OrderDetail> orderDetails = orderDao.queryProductDetail(orderCode);
        for (OrderDetail orderDetail : orderDetails) {
            if (StringUtils.isNotBlank(orderDetail.getExpressNum())) {
                list.add(orderDetail.getExpressNum());
            }
        }
        //去重
        for  ( int  i  =   0 ; i  <  list.size() - 1 ; i ++ )  {
            for  ( int  j  =  list.size() - 1 ; j > i; j -- )  {
                if  (list.get(j).equals(list.get(i)))  {
                    list.remove(j);
                }
            }
        }
        //判断是否有包裹
        if (list.size()>0) {
            for (int i=0;i<list.size();i++) {
                List<OrderDetail> orderDetailList = orderDao.queryInfoByExpressNum(orderCode,list.get(i));
                detailList.add(orderDetailList);
            }
            order.setPackageList(detailList);
        }
        order.setOrderDetailList(orderDetails);
        Refund refund = refundDao.queryRefundInfo(orderCode);
        if (refund != null) {
            order.setOrderStatus(refund.getOrderStatus());
            order.setRefundCode(refund.getRefundCode());
            order.setRefundId(refund.getRefundId());
        }
        //部分发货
        if ("send_some".equals(order.getStatus())) {
            OrderDetail orderDetail = orderDao.querySendSome(orderCode);
            order.setSendTime(orderDetail.getDetailSendTime());
        }
        //------------------------------
        Integer count = refundDao.countRefundingByOrderCode(orderCode);
        if (count > 0) {
            order.setAdditional(EnumOrderStatus.refunding.name());//售后中
        }
        if (org.apache.commons.lang3.StringUtils.equals(EnumOrderStatus.close.name(), order.getStatus()) && null != refund && 6 == refund.getRefundStatus()) {
            order.setAdditional(EnumOrderStatus.refund_success.name());//退款完成
        }
        CompanyInfo buyerInfo = companyInfoDao.queryCompanyInfo(order.getBuyerId());
        CompanyInfo sellerInfo = companyInfoDao.queryCompanyInfo(order.getSellerId());
        order.setBuyerInfo(buyerInfo);
        order.setSellerInfo(sellerInfo);
        return order;
    }

    /**
     * 线下转账
     *
     * @param transferAccountVo
     * @param request
     * @return
     */
    @Override
    @Transactional
    public CommonDto TransferAccount(TransferAccountVo transferAccountVo,HttpServletRequest request) {
        RequestPay requestPay = new RequestPay();
        String ip = IpUtil.getIpAddress(request);
        //线下操作人
        requestPay.setLine_payment(transferAccountVo.getAccountName());
        requestPay.setAccount_id(String.valueOf(transferAccountVo.getAccountId()));
        requestPay.setAccount_name(transferAccountVo.getAccountName());
        requestPay.setType(transferAccountVo.getType());
        requestPay.setChannel("user");//支付渠道
        requestPay.setOrder_code(transferAccountVo.getOrderCode());
        requestPay.setPrice(transferAccountVo.getPrice());
        requestPay.setPay_account(transferAccountVo.getPayerAccount());
        //附加信息
        requestPay.setPayer(transferAccountVo.getPayer());
        if (StringUtils.isNotBlank(transferAccountVo.getPayerBank())) {
            requestPay.setPayer_bank(transferAccountVo.getPayerBank());
        }
        requestPay.setReceivable(transferAccountVo.getReceivables());
        requestPay.setReceivable_bank(transferAccountVo.getReceivablesBank());
        requestPay.setReceivable_account(transferAccountVo.getReceivablesAccount());
        requestPay.setPay_time(transferAccountVo.getPayTime());
        try {
            //查询手机号并发送短信
            CompanyInfo companyInfo = companyInfoDao.queryCompanyInfo(transferAccountVo.getSellerId());
            if (companyInfo != null) {
                orderFeignApi.pay(requestPay);
            }
            String msg = "尊敬的商户您好，您在眼镜圈平台的店铺有新订单产生，请到“供货中心-我的供货单”及时处理发货";
            if (StringUtils.isNotBlank(companyInfo.getPaymsgPhone())) {
                smsService.sendTrigger(companyInfo.getPaymsgPhone(),msg);
            } else {
                smsService.sendTrigger(companyInfo.getContactsPhone(),msg);
            }
            //获取支付ip
            updatePayIp(transferAccountVo.getOrderCode(),request);
            //更新资金订单
            RequestTransferInitPay requestTransferInitPay = new RequestTransferInitPay();
            requestTransferInitPay.setTrade_code(transferAccountVo.getOrderCode());
            requestTransferInitPay.setPay_type(transferAccountVo.getType());
            requestTransferInitPay.setPay_ip(ip);
            //附加信息
            requestTransferInitPay.setPayer(transferAccountVo.getPayer());
            requestTransferInitPay.setPayer_bank(transferAccountVo.getPayerBank());
            requestTransferInitPay.setReceivable(transferAccountVo.getReceivables());
            requestTransferInitPay.setReceivable_bank(transferAccountVo.getReceivablesBank());
            requestTransferInitPay.setReceivable_account(transferAccountVo.getReceivablesAccount());
            payFeignApi.transferInitPay(requestTransferInitPay);


            RequestUpdateFundOrder requestUpdateFundOrder = new RequestUpdateFundOrder();
            requestUpdateFundOrder.setTrade_code(transferAccountVo.getOrderCode());
            requestUpdateFundOrder.setPay_status("success");
            requestUpdateFundOrder.setPay_code(transferAccountVo.getPayCode());
            requestUpdateFundOrder.setPay_type("TRANSFER_ACCOUNTS");
            requestUpdateFundOrder.setActual_pay(transferAccountVo.getPrice());
            requestUpdateFundOrder.setTrade_type("trade");
            payFeignApi.updateFundOrder(requestUpdateFundOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonDto(false);
        }
        return new CommonDto(true);
    }

    /**
     * 获取当前支付用户的ip
     * @param orderCode
     */
    public void updatePayIp(String orderCode, HttpServletRequest request) {
        String ip = IpUtil.getIpAddress(request);
        RequestMainOrder requestMainOrder = new RequestMainOrder();
        requestMainOrder.setOrder_code(orderCode);
        requestMainOrder.setPay_device_ip(ip);
        orderFeignApi.updateOrder(requestMainOrder);
    }
}
