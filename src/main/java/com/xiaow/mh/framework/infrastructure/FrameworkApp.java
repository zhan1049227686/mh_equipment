package com.xiaow.mh.framework.infrastructure;

/**
 * Created by zhangnengwen on 16/12/12.
 */


import com.xiaow.mh.framework.vc.AppErrorController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * 一些和业务无关的最底层配置
 */

@EnableSpringDataWebSupport
@SpringBootApplication(exclude = {
        RepositoryRestMvcAutoConfiguration.class,
        JmxAutoConfiguration.class,
        WebSocketAutoConfiguration.class,
        ActiveMQAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.xiaow.mh.framework"})

public abstract class FrameworkApp extends SpringBootServletInitializer {
}
