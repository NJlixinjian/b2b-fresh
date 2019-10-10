package cn.sigo.sigocloudprovider.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 订单状态状态
 *
 * @author bianzhiwen
 * @date 2019/7/3
 */
public enum EnumOrderStatus {

    /**
     * 等待买家付款
     */
    wait_for_pay(0, "待付款"),
    /**
     * 已付款待发货
     */
    paid_for_send(1, "待发货"),
    /**
     * 已发货
     */
    sending(2, "已发货"),
    /**
     * 部分发货
     */
    send_some(3, "部分发货"),
    /**
     * 已完成
     */
    finish(4, "交易成功"),
    /**
     * 已关闭
     */
    close(5, "交易关闭"),
    /**
     * 已取消
     */
    cancel(6, "交易取消"),
    /**
     * 退款中
     */
    refunding(7, "售后中"),
    /**
     * 退款完成
     */
    refund_success(9, "退款完成"),
    /**
     * 编辑历史状态
     */
    history(8, "历史数据");

    private int value;

    private String keyValue;

    public int getValue() {
        return value;
    }

    public String getKeyValue() {
        return keyValue;
    }

    EnumOrderStatus(int value, String keyValue) {
        this.value = value;
        this.keyValue = keyValue;
    }

    public String getKeyValueByName(String name) {
        if (StringUtils.equals(name, name())) {
            return keyValue;
        }
        return null;
    }

    public static void main(String[] args) {
        EnumOrderStatus wait_for_pay = EnumOrderStatus.valueOf("wait_for_pay");
        String keyValue = wait_for_pay.getKeyValue();
        System.out.println("keyValue: " + keyValue);
    }
}
