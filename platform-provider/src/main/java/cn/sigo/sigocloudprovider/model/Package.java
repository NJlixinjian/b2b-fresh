package cn.sigo.sigocloudprovider.model;

import java.util.Date;
import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/9/10 10:31
 * @Description:
 */
public class Package {

    private String expressNum;//快递单号

    private String expressCompany;

    private Date detailSendTime;//子订单发货时间

    private String consigneeName;//收货人姓名

    private String consigneePhone;//收货人手机

    private String province;

    private String city;

    private String district;//区县

    private String street;//乡镇街道

    private String addressDetail;//详细地址

    private List<OrderDetail> orderDetailList;//订单详情

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public Date getDetailSendTime() {
        return detailSendTime;
    }

    public void setDetailSendTime(Date detailSendTime) {
        this.detailSendTime = detailSendTime;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
