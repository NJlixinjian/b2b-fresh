package cn.sigo.sigocloudprovider;

import cn.sigo.sigocloudprovider.config.serverport.RandomSerPortEnvironmentPreparedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
// 服务发现与注册
@EnableDiscoveryClient
// 远程调用
@EnableFeignClients("cn.sigo")
@EnableHystrixDashboard
@EnableCircuitBreaker
@Configuration
@MapperScan("cn.sigo.sigocloudprovider.dao")
public class SigoCloudProviderApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SigoCloudProviderApplication.class);
        // 启动随机端口
        app.addListeners(new RandomSerPortEnvironmentPreparedEventListener());
        app.run(args);
    }
}
