package com.demo.infohub.serviceimpl.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.demo.infohub")
@EntityScan(basePackages = "com.demo.infohub.models")
// @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DaoConfiguration {

	// @Bean
	// AuditorAware<String> auditorProvider() {
	// return new AuditorAwareImpl();
	// }
}