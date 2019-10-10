package cn.sigo.sigocloudprovider.elastic.model;

/**
 * @Auther: lxj
 * @Date: 2019/8/26 17:36
 * @Description:
 */
public class StateNum {

    private long waitForPay;

    private long paidForSend;

    private long sending;

    private long finish;//交易成功

    private long close;//交易关闭

    private long cancel;//交易取消

    private long sendSome;//部分发货

    public long getSendSome() {
        return sendSome;
    }

    public void setSendSome(long sendSome) {
        this.sendSome = sendSome;
    }

    public long getWaitForPay() {
        return waitForPay;
    }

    public void setWaitForPay(long waitForPay) {
        this.waitForPay = waitForPay;
    }

    public long getPaidForSend() {
        return paidForSend;
    }

    public void setPaidForSend(long paidForSend) {
        this.paidForSend = paidForSend;
    }

    public long getSending() {
        return sending;
    }

    public void setSending(long sending) {
        this.sending = sending;
    }

    public long getFinish() {
        return finish;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    public long getClose() {
        return close;
    }

    public void setClose(long close) {
        this.close = close;
    }

    public long getCancel() {
        return cancel;
    }

    public void setCancel(long cancel) {
        this.cancel = cancel;
    }
}
