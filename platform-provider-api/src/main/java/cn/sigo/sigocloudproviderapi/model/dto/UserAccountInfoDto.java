package cn.sigo.sigocloudproviderapi.model.dto;

import lombok.Data;

/**
 * 用户账户传输对象
 * 非数据库Model对象，按需定义
 * 不推荐直接使用数据库实体类直接暴露
 */
@Data
public class UserAccountInfoDto {
    private String username;
    private Long userid;
}
