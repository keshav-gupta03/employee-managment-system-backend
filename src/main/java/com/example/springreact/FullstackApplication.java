package com.example.springreact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



//@Configuration
//@EnableAutoConfiguration
//@ComponentScan("com.example")
@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class FullstackApplication {
	public static void main(String[] args) {
		SpringApplication.run(FullstackApplication.class, args);
	}
	
	
}
