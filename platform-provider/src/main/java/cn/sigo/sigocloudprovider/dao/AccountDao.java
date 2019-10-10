package cn.sigo.sigocloudprovider.dao;

import cn.sigo.sigocloudprovider.model.AccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户DAO
 */
@Mapper
public interface AccountDao extends BaseMapper<AccountEntity> {

}
