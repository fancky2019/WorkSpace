package com.fancky.multipledatasource.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//指定该SqlSession对象对应的dao(basePackages , dao扫包  sqlSessionFactoryRef: SqlSessionFactory对象注入到该变量中)
@MapperScan(basePackages = "com.fancky.multipledatasource.dao.test", sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestDataSourceConfig {
   // TestDataSourceConfig
//    /**
//     * 封装数据源对象创建, 该方法就已经将数据源的各个数据封装到该对象中
//     *
//     * @return
//     */
//    @Bean(name = "testDataSource")
//    @Primary //必须要有, 说明该数据源是默认数据源
//    @ConfigurationProperties(prefix = "spring.datasource.test") //读取的数据源前缀, 跟yml文件对应
//    public DataSource testDataSource() {
//        //不用多数据源事务
//        return DataSourceBuilder.create().build();
//    }

    /**
     * SqlSession对象创建
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "testSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //指定起别名的包, 这里注意, 设置该参数时打成jar启动会找不到该包下的类,目前未找到解决方案
        bean.setTypeAliasesPackage("com.example.springboot.pojo");
        bean.setDataSource(dataSource);
        //指定该SqlSession对应的mapper.xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test/*.xml"));
        return bean.getObject();
    }

    @Bean("testSqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("testSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }



    @Bean(name = "testDataSource")
    public DataSource testDataSource(TestDataSourceConfigParameters testConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(testConfig.getJdbcUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(testConfig.getPassword());
        mysqlXaDataSource.setUser(testConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("testDataSource");

        xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(testConfig.getTestQuery());
        return xaDataSource;
    }





}
