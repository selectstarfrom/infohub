package com.demo.infohub.serviceimpl.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;

//@IAppLauncher
//@EnableJpaRepositories(basePackages = "com.gits.cdoc.service.impl.dao")
@EntityScan(basePackages = "com.gits.cdoc.model.entity")
public class DaoApplication {
}