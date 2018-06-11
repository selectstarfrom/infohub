//package com.gits.cdoc.service.impl.dao.config;
//
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//import com.gits.cdoc.model.core.AuditorAwareImpl;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.gits.cdoc.service.impl.dao")
//@EntityScan(basePackages = "com.gits.cdoc.model.entity")
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//public class DaoConfiguration {
//
//	@Bean
//	AuditorAware<String> auditorProvider() {
//		return new AuditorAwareImpl();
//	}
//}