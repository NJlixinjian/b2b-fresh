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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Auther: lxj
 * @Date: 2019/9/9 17:08
 * @Description:
 */
@Configuration
@MapperScan(basePackages = SpartanDatabaseConfig.PACKAGE, sqlSessionFactoryRef = "spartanSqlSessionFactory")
public class SpartanDatabaseConfig {
    /**
     * 对应的mysql dao层包
     */
    static final String PACKAGE = "cn.sigo.sigocloudprovider.dao.spartan";
    /**
     * 对应mysql的mapper.xml文件
     */
    static final String MAPPER_LOCATION = "classpath:mapper/spartan/*.xml";

    @Bean(name = "spartanDataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.datasource.spartan")
    public DataSource spartanDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "spartanTransactionManager")
    @Primary
    public DataSourceTransactionManager spartanTransactionManager() {
        return new DataSourceTransactionManager(spartanDataSource());
    }

    @Bean(name = "spartanSqlSessionFactory")
    @Primary
    public SqlSessionFactory spartanSqlSessionFactory(@Qualifier("spartanDataSource") DataSource spartanDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(spartanDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(SpartanDatabaseConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
