package com.zouls.mysqlmulti.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zouls.mysqlmulti.mapper.datasource1", sqlSessionTemplateRef = "datasource1SqlSessionTemplate")
public class Datasource1Config {

    @Bean
    @ConfigurationProperties("datasource1.datasource")
    public DataSourceProperties datasource1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource datasource1DataSource() {
        DataSourceProperties datasource1DataSourceProperties = datasource1DataSourceProperties();
        return datasource1DataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "datasource1SqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("datasource1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/datasource1/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "datasource1TransactionManager")
    public DataSourceTransactionManager createTransactionManager(@Qualifier("datasource1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datasource1SqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("datasource1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
