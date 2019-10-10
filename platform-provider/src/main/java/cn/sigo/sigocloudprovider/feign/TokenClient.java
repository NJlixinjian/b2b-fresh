package cn.sigo.sigocloudprovider.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(url = "${url.erpapi}", name = "gettoken")
public interface TokenClient {

    @PostMapping(value = "ums/Token",
            consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String getToken(String entityBody);
}
