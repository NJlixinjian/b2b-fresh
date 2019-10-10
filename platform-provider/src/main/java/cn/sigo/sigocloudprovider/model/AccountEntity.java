package cn.sigo.sigocloudprovider.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体
 */
@Data
@TableName("account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}