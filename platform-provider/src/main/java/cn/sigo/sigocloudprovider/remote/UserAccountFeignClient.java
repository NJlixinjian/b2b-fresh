package cn.sigo.sigocloudprovider.remote;

import cn.sigo.sigocloudprovider.handler.Http400Exception;
import cn.sigo.sigocloudprovider.model.AccountEntity;
import cn.sigo.sigocloudprovider.service.UserService;
import cn.sigo.sigocloudproviderapi.model.dto.UserAccountInfoDto;
import cn.sigo.sigocloudproviderapi.service.UserAccountFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户微服务实现，非前端交互部分、可能会服务间调用的接口
 * @RequestBody 必选
 */
@RestController
public class UserAccountFeignClient implements UserAccountFeignApi {

    @Autowired
    UserService userService;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserAccountInfoDto getUserInfoByUserId(@RequestParam(value = "userid") Long userId) {
        AccountEntity userEntity = userService.getById(userId);
        UserAccountInfoDto dto = new UserAccountInfoDto();
        if (userEntity != null) {
            dto.setUsername(userEntity.getUsername());
            dto.setUserid(userEntity.getUserid());
        } else {
           throw new Http400Exception("usernotfound", "指定用户不存在");
        }
        return dto;
    }
}
