//package cn.sigo.sigocloudprovider.elastic.service;
//
//import com.alibaba.fastjson.JSON;
//import com.sigo.spartan.search.common.ElasticKey;
//import com.spartan.service.redis.RedisKey;
//import com.spartan.service.redis.RedisService;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.net.InetAddress;
//import java.util.Enumeration;
//import java.util.Properties;
//
///**
// * properties
// * 获取 es ip端口
// * @auther B2Badmin
// */
//public class ElasticService {
//
//
//    @Autowired
//    private RedisService redisService;
//
//    //properties 名称
//    private final String CONFIG_PROPERTIES = "config.properties";
//
//    //properties es
//    private final String SPRING_ES_IP = "spring.es.ip";
//    private final String SPRING_ES_PORT = "spring.es.port";
//    private final String SPRING_ES_CLUSTER_NAME = "spring.es.cluster.name";
//    private final String MAIN_URL = "spring.main.url";
//
//    //es 客户端
//    public TransportClient getElasticClient() throws Exception {
//        ElasticEntity elasticEntity = this.getElasticInfo();
//        //制定es集群
//        Settings settings = Settings.builder().put(ElasticKey.CLUSTER_NAME, elasticEntity.getClusterName()).build();
//        //创建访问es服务器的客户端
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(elasticEntity.getIp()), elasticEntity.getPort()));
//       /* //异步处理  关闭client资源
//        Executors.newFixedThreadPool(5).execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(60000);//1分钟
//                    client.close();
//                } catch (Exception e) {
//                    logger.error("elasticsearch资源client关闭失败>>>>>>>>>>>>", e);
//                }
//            }
//        });*/
//        return client;
//    }
//
//    //获取es  ip端口
//    private ElasticEntity getElasticInfo() throws Exception {
//        String esKey = RedisKey.ES_IP_KEY;//es redis key
//        String esValue = redisService.getStr(esKey);//查询es ip端口 是否存在
//        if (esValue != null) {//如果redis有值，返回redis值
//            return JSON.parseObject(esValue, ElasticEntity.class);
//        } else {//如果redis没有值，封装数据存入redis，返回值
//            ElasticService propertiesUtil = new ElasticService();
//            redisService.setStr(esKey, propertiesUtil.getProperties());
//            redisService.expire(esKey, RedisKey.INVALID_ONE_HOUR);//失效时间
//            return JSON.parseObject(propertiesUtil.getProperties(), ElasticEntity.class);
//        }
//    }
//
//    //封装 properties 数据
//    private String getProperties() throws Exception {
//        ElasticEntity elasticEntity = new ElasticEntity();
//        //获取项目文件路径
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        System.out.println("================================================="+path);
//
//        //得到配置文件的名字
//        Properties pps = new Properties();
//        pps.load(new FileInputStream(path + CONFIG_PROPERTIES));
//        Enumeration enum1 = pps.propertyNames();
//        while (enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            if (strKey.equals(SPRING_ES_IP)) {
//                elasticEntity.setIp(pps.getProperty(strKey));
//            } else if (strKey.equals(SPRING_ES_PORT)) {
//                elasticEntity.setPort(Integer.parseInt(pps.getProperty(strKey)));
//            } else if (strKey.equals(SPRING_ES_CLUSTER_NAME)) {
//                elasticEntity.setClusterName(pps.getProperty(strKey));
//            }
//        }
//        return JSON.toJSONString(elasticEntity);
//    }
//
//    //封装 properties 数据
//    private String getPayProperties() throws Exception {
//        ElasticEntity elasticEntity = new ElasticEntity();
//        //获取项目文件路径
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        System.out.println("================================================="+path);
//
//        //得到配置文件的名字
//        Properties pps = new Properties();
//        pps.load(new FileInputStream(path + CONFIG_PROPERTIES));
//        Enumeration enum1 = pps.propertyNames();
//        while (enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            if (strKey.equals(SPRING_ES_IP)) {
//                elasticEntity.setIp(pps.getProperty(strKey));
//            } else if (strKey.equals(SPRING_ES_PORT)) {
//                elasticEntity.setPort(Integer.parseInt(pps.getProperty(strKey)));
//            } else if (strKey.equals(SPRING_ES_CLUSTER_NAME)) {
//                elasticEntity.setClusterName(pps.getProperty(strKey));
//            }
//        }
//        return JSON.toJSONString(elasticEntity);
//    }
//}
