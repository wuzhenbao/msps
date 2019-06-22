package com.wuzhenbao.it.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wuzhenbao.it.dao",sqlSessionFactoryRef = "commonSessionFactory")
public class DataSourceConfig {
    @Autowired
    @Qualifier("myDataSource")
    private DataSource datasource;

    @Bean("commonSessionFactory")
    public SqlSessionFactory commonSessionFactory() throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:com/wuzhenbao/it/dao/user/mapper/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

}
