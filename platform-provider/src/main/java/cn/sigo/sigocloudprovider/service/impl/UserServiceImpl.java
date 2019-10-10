package cn.sigo.sigocloudprovider.service.impl;

import cn.sigo.sigocloudprovider.dao.AccountDao;
import cn.sigo.sigocloudprovider.model.AccountEntity;
import cn.sigo.sigocloudprovider.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements UserService  {

}
