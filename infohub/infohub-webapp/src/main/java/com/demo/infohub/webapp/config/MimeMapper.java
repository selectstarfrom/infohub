//package com.demo.infohub.webapp.config;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.server.MimeMappings;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
///**
// * @author imfroz
// *
// */
//@Configuration
//public class MimeMapper implements EmbeddedServletContainerCustomizer {
//
//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer container) {
//
//        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
//        mappings.add("eot", "application/vnd.ms-fontobject");
//        mappings.add("ttf", "application/font-sfnt");
//        mappings.add("woff", "application/font-woff");
//        mappings.add("woff2", "application/font-woff2");
//        mappings.add("woff2", "application/font-woff2");
//        mappings.add("eot?#iefix", "application/vnd.ms-fontobject");
//        mappings.add("svg", "image/svg+xml");
//        mappings.add("svg#exosemibold", "image/svg+xml");
//        mappings.add("svg#exobolditalic", "image/svg+xml");
//        mappings.add("svg#exomedium</extension>", "image/svg+xml");
//        mappings.add("svg#exoregular", "image/svg+xml");
//        mappings.add("svg#fontawesomeregular", "image/svg+xml");
//
//        container.setMimeMappings(mappings);        
//        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/views/pg-landing.xhtml"));
//        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/views/pg-landing.xhtml"));
//    }
//}