package com.demo.infohub.serviceimpl.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.gits.cdoc.service.impl" })
@EnableAutoConfiguration
//@Import({ DaoConfiguration.class })
public class ServiceConfiguration {
//    @Bean
//    public ShaPasswordEncoder encoder() {
//        return new ShaPasswordEncoder(256);
//    }
}