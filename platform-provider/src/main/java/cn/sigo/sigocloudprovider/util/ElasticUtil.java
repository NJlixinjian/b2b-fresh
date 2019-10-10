package cn.sigo.sigocloudprovider.util;

import cn.sigo.sigocloudprovider.elastic.service.ElasticMerchantKey;
import cn.sigo.sigocloudprovider.elastic.service.ElasticOrderKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * @Auther: lxj
 * @Date: 2019/9/26 18:38
 * @Description:
 */
@RestController
public class ElasticUtil {
    private TransportClient client = null;

    @Value("${spring.es.cluster.name}")
    public  String SPRING_ES_CLUSTER_NAME;

    @Value("${spring.es.ip}")
    public String SPRING_ES_IP;

    @Value("${spring.es.port}")
    public  Integer SPRING_ES_PORT;
    /**
     * 删除数据根据id
     *
     * @param id
     * @throws Exception
     */
    @RequestMapping(value = "/es/del-product", method = RequestMethod.GET)
    public void deleteTest(String id) throws Exception{
        try {
            // 创建client
            //创建es客户端
            Settings settings = Settings.builder().put(ElasticOrderKey.CLUSTER_NAME, SPRING_ES_CLUSTER_NAME).build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(SPRING_ES_IP), SPRING_ES_PORT));
            DeleteResponse response =
                    client.prepareDelete(ElasticMerchantKey.INDEX_MERCHANT_SHOP, ElasticMerchantKey.MERCHANT_TYPE, id).execute().actionGet();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
