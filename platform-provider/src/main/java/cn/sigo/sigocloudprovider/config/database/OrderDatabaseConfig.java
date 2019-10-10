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
 * @Date: 2019/9/9 17:01
 * @Description:
 */
@Configuration
@MapperScan(basePackages = OrderDatabaseConfig.PACKAGE, sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderDatabaseConfig {
    /**
     * 对应的mysql dao层包
     */
    static final String PACKAGE = "cn.sigo.sigocloudprovider.dao.order";
    /**
     * 对应mysql的mapper.xml文件
     */
    static final String MAPPER_LOCATION = "classpath:mapper/order/*.xml";

    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.orders")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "orderTransactionManager")
    public DataSourceTransactionManager orderTransactionManager(@Qualifier("orderDataSource") DataSource orderDataSource) {
        return new DataSourceTransactionManager(orderDataSource);
    }

    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource orderDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(orderDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(OrderDatabaseConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
