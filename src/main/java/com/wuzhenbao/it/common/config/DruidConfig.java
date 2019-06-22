package com.wuzhenbao.it.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DruidConfig {
    private static final Logger webLog = LoggerFactory.getLogger(DruidConfig.class);

    @Component
    @ConfigurationProperties(prefix = "spring")
    public class DruidCommConfig{
        private Map<String,String> dataSource;

        public Map<String, String> getDataSource() {
            return dataSource;
        }

        public void setDataSource(Map<String, String> dataSource) {
            this.dataSource = dataSource;
        }
    }

    @Autowired
    private DruidCommConfig commonConfig;

    @Bean(name="myDataSource")
    @Qualifier("myDataSource")
    public DataSource myDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
                                   @Value("spring.datasource.url")String url,
                                   @Value("spring.datasource.username")String username,
                                   @Value("spring.datasource.password")String pwword){
        DruidDataSource dataSource = null;
        try{
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(commonConfig.getDataSource());
        }catch (Exception ex){
            webLog.error(String.format("数据源【url=%s】创建异常",url));
            return null;
        }
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setPassword(pwword);
        dataSource.setUsername(username);
        return dataSource;
    }

    @Bean
    @Qualifier("commonTXManager")
    public PlatformTransactionManager commonTranManager(@Qualifier("myDataSource") DataSource myDataSource){
        return new DataSourceTransactionManager(myDataSource);
    }
}
