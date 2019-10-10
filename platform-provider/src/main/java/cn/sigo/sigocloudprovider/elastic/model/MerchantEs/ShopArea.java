package cn.sigo.sigocloudprovider.elastic.model.MerchantEs;


public class ShopArea {
    private GeoPoint geo_point;

    private AddressInfo province;

    private AddressInfo city;

    private AddressInfo district;

    private String address_detail;

    public GeoPoint getGeo_point() {
        return geo_point;
    }

    public void setGeo_point(GeoPoint geo_point) {
        this.geo_point = geo_point;
    }

    public AddressInfo getProvince() {
        return province;
    }

    public void setProvince(AddressInfo province) {
        this.province = province;
    }

    public AddressInfo getCity() {
        return city;
    }

    public void setCity(AddressInfo city) {
        this.city = city;
    }

    public AddressInfo getDistrict() {
        return district;
    }

    public void setDistrict(AddressInfo district) {
        this.district = district;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }
}
