package cn.sigo.sigocloudproviderapi.service.hystrix;

import cn.sigo.sigocloudproviderapi.model.dto.UserAccountInfoDto;
import cn.sigo.sigocloudproviderapi.service.UserAccountFeignApi;

/**
 * 用户账户服务熔断处理
 */
public class UserAccountFeignHystrix implements UserAccountFeignApi {

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserAccountInfoDto getUserInfoByUserId(Long userId) {
        return null;
    }
}
