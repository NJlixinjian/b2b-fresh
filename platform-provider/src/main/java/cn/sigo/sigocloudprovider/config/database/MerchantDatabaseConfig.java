package cn.sigo.sigocloudprovider.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 18:38
 * @Description:
 */
@Configuration
@MapperScan(basePackages = MerchantDatabaseConfig.PACKAGE, sqlSessionFactoryRef = "merchantSqlSessionFactory")
public class MerchantDatabaseConfig {
    /**
     * 对应的mysql dao层包
     */
    static final String PACKAGE = "cn.sigo.sigocloudprovider.dao.merchant";
    /**
     * 对应mysql的mapper.xml文件
     */
    static final String MAPPER_LOCATION = "classpath:mapper/merchant/*.xml";

    @Bean(name = "merchantDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.merchant")
    public DataSource merchantDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "merchantTransactionManager")
    public DataSourceTransactionManager merchantTransactionManager(@Qualifier("merchantDataSource") DataSource merchantDataSource) {
        return new DataSourceTransactionManager(merchantDataSource);
    }

    @Bean(name = "merchantSqlSessionFactory")
    public SqlSessionFactory merchantSqlSessionFactory(@Qualifier("merchantDataSource") DataSource merchantDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(merchantDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MerchantDatabaseConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
