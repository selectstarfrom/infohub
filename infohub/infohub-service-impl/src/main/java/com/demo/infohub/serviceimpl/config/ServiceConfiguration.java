package com.demo.infohub.serviceimpl.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.infohub.serviceimpl.dao.config.DaoConfiguration;

@ComponentScan(basePackages = { "com.demo.infohub" })
@EnableAutoConfiguration
@EnableJpaRepositories
@Import({ DaoConfiguration.class })
public class ServiceConfiguration {
	// @Bean
	// public ShaPasswordEncoder encoder() {
	// return new ShaPasswordEncoder(256);
	// }
}