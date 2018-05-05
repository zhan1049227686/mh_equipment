//package com.xiaow.mh.framework.datasource;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * Created by zhangnengwen on 16/12/26.
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryPrimary",
//        transactionManagerRef="transactionManagerPrimary",
//        basePackages= { "com.xiaow.mh.business.domain"}) //设置Repository所在位置
//public class RepositoryPrimaryConfig {
//
//    @Autowired
//    @Qualifier("primaryDs")
//    private DataSource primaryDs;
//
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Bean(name = "entityManagerPrimary")
//    @Primary
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(primaryDs)
//                .properties(getVendorProperties(primaryDs))
//                .packages("com.xiaow.mh.business.domain") //设置实体类所在位置
//                .persistenceUnit("primaryPersistenceUnit")
//                .build();
//    }
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
//    }
//}
