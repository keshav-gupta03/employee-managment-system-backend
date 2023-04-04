package com.example.springreact;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.springreact.filter.CrossOriginFilter;

@Configuration
@Order(1)
public class AppConfig {

	@Bean
	public FilterRegistrationBean corsFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(crossOriginFilter());
	    registration.addUrlPatterns("/api/v1/*");
	    registration.setName("cross origin filter");
	    registration.setOrder(1);
	    return registration;
	}
	
	public Filter crossOriginFilter() {
	    return new CrossOriginFilter();
	}
}
