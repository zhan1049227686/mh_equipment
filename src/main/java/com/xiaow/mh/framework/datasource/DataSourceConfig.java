//package com.xiaow.mh.framework.datasource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * Created by zhangnengwen on 16/12/26.
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name = "primaryDs")
//    @Qualifier("primaryDS")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.primary.datasource")
//    public DataSource primaryDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//}
