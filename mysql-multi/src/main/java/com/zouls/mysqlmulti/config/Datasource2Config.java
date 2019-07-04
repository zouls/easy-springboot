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
@MapperScan(basePackages = "com.zouls.mysqlmulti.mapper.datasource2", sqlSessionTemplateRef = "datasource2SqlSessionTemplate")
public class Datasource2Config {

    @Bean
    @ConfigurationProperties("datasource2.datasource")
    public DataSourceProperties datasource2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource datasource2DataSource() {
        DataSourceProperties datasource2DataSourceProperties = datasource2DataSourceProperties();
        return datasource2DataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "datasource2SqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("datasource2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/datasource2/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "datasource2TransactionManager")
    public DataSourceTransactionManager createTransactionManager(@Qualifier("datasource2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datasource2SqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("datasource2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
