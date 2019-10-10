package cn.sigo.sigocloudprovider.elastic.service;

/**
 * es 属性值
 * @auther B2Badmin
 */
public class ElasticEntity {

    private String ip;//es  ip
    private Integer port;// es 端口
    private String clusterName; //es 集群名称

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
