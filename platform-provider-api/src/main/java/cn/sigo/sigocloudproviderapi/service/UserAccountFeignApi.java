package cn.sigo.sigocloudproviderapi.service;

import cn.sigo.sigocloudproviderapi.model.dto.UserAccountInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户账户体系对外提供微服务接口
 * name 这里name是yml中配置的application name
 */
@FeignClient(name= "sigo-cloud-platform")
public interface UserAccountFeignApi {
    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(name = "/userinfo/{userid}",method = RequestMethod.GET)
    UserAccountInfoDto getUserInfoByUserId(@RequestParam(value = "userid") Long userId);
}
