package com.fileupload.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fileupload")
public class MyAppConfig {

	@Bean
	public ViewResolver getViewResolver()
	{
		InternalResourceViewResolver views = new InternalResourceViewResolver();
		views.setSuffix(".jsp");
		views.setPrefix("/WEB-INF/views/");
		return views;
	}
	
	@Bean
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource =new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/fileDemo");
		dataSource.setUsername("root");
		dataSource.setPassword("955455Praveen");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(5242880);
		return resolver;
	}
	
}
