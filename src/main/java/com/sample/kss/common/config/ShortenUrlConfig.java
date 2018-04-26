package com.sample.kss.common.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Application Config
 * @author seungshins
 *
 */
public class ShortenUrlConfig {
	@Bean
	public ServletRegistrationBean servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/Y*");
		registration.addUrlMappings("/K*");
		registration.addUrlMappings("/G*");
		registration.addUrlMappings("/F*");
		registration.addUrlMappings("/U*");
		registration.addUrlMappings("/console/*");
		return registration;
	}
}
