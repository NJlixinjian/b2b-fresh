package cn.sigo.sigocloudprovider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统参数
 */
@Component
public class SystemConfig {
    @Value("${title:默认}")
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
