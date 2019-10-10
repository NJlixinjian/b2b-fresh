package cn.sigo.sigocloudprovider.elastic.model;

import java.io.Serializable;

/**
 * @Auther: lxj
 * @Date: 2019/8/26 14:55
 * @Description:
 */
public class Statistics implements Serializable{

    //求和
    private Double sum;

    //统计数量
    private long count;

    //平均值
    private Double avg;

    //交易总金额
    private Double sumActualPay;

    //退款金额
    private Double refundPay;

    //成交额
    private Double successPay;

    //买家数量
    private Object buyerNum;

    public Object getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(Object buyerNum) {
        this.buyerNum = buyerNum;
    }

    public Double getRefundPay() {
        return refundPay;
    }

    public void setRefundPay(Double refundPay) {
        this.refundPay = refundPay;
    }

    public Double getSuccessPay() {
        return successPay;
    }

    public void setSuccessPay(Double successPay) {
        this.successPay = successPay;
    }

    public Double getSumActualPay() {
        return sumActualPay;
    }

    public void setSumActualPay(Double sumActualPay) {
        this.sumActualPay = sumActualPay;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
