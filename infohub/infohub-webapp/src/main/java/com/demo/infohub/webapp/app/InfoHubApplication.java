package com.demo.infohub.webapp.app;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.ProjectStage;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.apache.catalina.Context;
import org.primefaces.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ServletContextAware;

import com.demo.infohub.serviceimpl.config.ServiceConfiguration;
import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.FacesInitializer;

@Configuration
@ComponentScan(basePackages = "com.demo.infohub")
@EnableAutoConfiguration
@Import({ ServiceConfiguration.class })
public class InfoHubApplication extends SpringBootServletInitializer implements ServletContextAware {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Dubai"));
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InfoHubApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InfoHubApplication.class);
	}

	@Bean
	public ServletContextInitializer servletContextCustomizer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext sc) throws ServletException {

				// sc.setInitParameter("javax.faces.FACELETS_LIBRARIES",
				// "/WEB-INF/primefaces-avalon.taglib.xml");
				// sc.setInitParameter(Constants.ContextParams.THEME, "avalon-firewatch");
				sc.setInitParameter(Constants.ContextParams.FONT_AWESOME, "true");
				sc.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
				sc.setInitParameter("com.sun.faces.injectionProvider", "com.softwood.SpringInjectionProvider");
				sc.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
				sc.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
				sc.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", "true");
			}
		};
	}

	@Bean
	// @ConditionalOnMissingBean(NonEmbeddedServletContainerFactory.class)
	public TomcatServletWebServerFactory embeddedServletContainerFactory() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

		tomcat.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				context.addServletContainerInitializer(new FacesInitializer(),
						getServletContainerInitializerHandlesTypes(FacesInitializer.class));

				context.addWelcomeFile("views/index.xhtml");

				context.addMimeMapping("eot", "application/vnd.ms-fontobject");
				context.addMimeMapping("ttf", "application/font-sfnt");
				context.addMimeMapping("woff", "application/font-woff");
				context.addMimeMapping("woff2", "application/font-woff2");
				context.addMimeMapping("woff2", "application/font-woff2");
				context.addMimeMapping("eot?#iefix", "application/vnd.ms-fontobject");
				context.addMimeMapping("svg", "image/svg+xml");
				context.addMimeMapping("svg#exosemibold", "image/svg+xml");
				context.addMimeMapping("svg#exobolditalic", "image/svg+xml");
				context.addMimeMapping("svg#exomedium</extension>", "image/svg+xml");
				context.addMimeMapping("svg#exoregular", "image/svg+xml");
				context.addMimeMapping("svg#fontawesomeregular", "image/svg+xml");
			}
		});

		return tomcat;
	}

	@SuppressWarnings("rawtypes")
	private Set<Class<?>> getServletContainerInitializerHandlesTypes(
			Class<? extends ServletContainerInitializer> sciClass) {
		HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
		if (annotation == null) {
			return Collections.emptySet();
		}

		Class[] classesArray = annotation.value();
		Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
		for (Class clazz : classesArray) {
			classesSet.add(clazz);
		}

		return classesSet;
	}

	@Bean
	public ServletRegistrationBean facesServletRegistraiton() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(),
				new String[] { "*.xhtml" });
		registration.setName("Faces Servlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

}
