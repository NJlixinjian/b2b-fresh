package cn.sigo.sigocloudprovider.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(url = "${url.erpapi}", name = "getlogisticsrecord")
public interface DeliveryClient {

    @PostMapping(value = "wms/logistics",
            consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String getDelivery(String entityBody);
}
