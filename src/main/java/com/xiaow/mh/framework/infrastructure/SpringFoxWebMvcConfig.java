package com.xiaow.mh.framework.infrastructure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by POJO on 6/27/16.
 */
@Configuration
@EnableWebMvc
public class SpringFoxWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/templates/css/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://toto.masadora.co:2333", "http://moecolle.masadora.co:9999","http://masadora.net", "http://www.masadora.net", "http://test.masadora.co", "http://127.0.0.1:1024", "http://192.168.2.104:1024", "http://moecolle.masadora.co", "http://circle.masadora.co", "http://circle.moecolle.net")
                .maxAge(450450450)
                .allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS")
                //    .allowedHeaders()
                .allowedHeaders("Origin",
                        "Accept-Language",
                        "Accept-Encoding",
                        "X-Forwarded-For",
                        "Connection",
                        "Accept",
                        "User-Agent",
                        "Host",
                        "Referer",
                        "Cookie",
                        "Content-Type",
                        "Cache-Control",
                        "If-Modified-Since",
                        "Pragma");
        // .allowCredentials(false).maxAge(3600);
    }

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        Stream.of("http://toto.masadora.co:2333", "http://moecolle.masadora.co:9999","http://masadora.net", "http://www.masadora.net", "http://test.masadora.co", "http://127.0.0.1:1024", "http://192.168.2.104:1024", "http://moecolle.masadora.co", "http://circle.masadora.co", "http://circle.moecolle.net")
                .forEach(config::addAllowedOrigin);
        Stream.of("Origin",
                "Accept-Language",
                "Accept-Encoding",
                "X-Forwarded-For",
                "Connection",
                "Accept",
                "User-Agent",
                "Host",
                "Referer",
                "Cookie",
                "Content-Type",
                "Cache-Control",
                "If-Modified-Since",
                "Pragma",
                "*").forEach(config::addAllowedHeader);
        Stream.of("PUT", "DELETE", "POST", "GET", "OPTIONS")
                .forEach(config::addAllowedMethod);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof AbstractJackson2HttpMessageConverter) {
                AbstractJackson2HttpMessageConverter c = (AbstractJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = c.getObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            }
        }

        super.extendMessageConverters(converters);
    }


}
