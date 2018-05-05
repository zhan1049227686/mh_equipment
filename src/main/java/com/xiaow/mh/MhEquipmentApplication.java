package com.xiaow.mh;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.xiaow.mh.framework.infrastructure.FrameworkApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@EnableJpaRepositories(basePackages = "com.xiaow.mh.business")
@EntityScan(basePackages = {"com.xiaow.mh.business.domain"})
@Configuration
public class MhEquipmentApplication extends FrameworkApp {

	public static void main(String[] args) {
		SpringApplication.run(MhEquipmentApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MhEquipmentApplication.class);
	}

	@Bean
	public Jackson2ObjectMapperBuilder configureObjectMapper() {
		return new Jackson2ObjectMapperBuilder()
				.modulesToInstall(Hibernate4Module.class);
	}
}
