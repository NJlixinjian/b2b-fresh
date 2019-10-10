package cn.sigo.sigocloudprovider.elastic.model.MerchantEs;


public class Entiterprise {
    private String entity_type;

    private String entity_type_key;

    private String entity_name;

    private String business_license_code;

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getEntity_type_key() {
        return entity_type_key;
    }

    public void setEntity_type_key(String entity_type_key) {
        this.entity_type_key = entity_type_key;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getBusiness_license_code() {
        return business_license_code;
    }

    public void setBusiness_license_code(String business_license_code) {
        this.business_license_code = business_license_code;
    }
}
