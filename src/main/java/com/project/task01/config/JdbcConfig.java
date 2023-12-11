package com.project.task01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 /*
		This class is responsible for connecting our program to database
		The value of url, userName, Password and driverClassName are configured
		on application.properties file
		I don't want to expose url, userName, password and DriverClassName
		so that i configured on it on application.properties file
 */

@Configuration
public class JdbcConfig {
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String userName;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

/* Creating this bean to declare a method as a bean producer.
	It will identify methods annotated with @Bean and register the
	returned object as a bean in the Spring application context.
 */
  @Bean
  public DataSource dataSource() {
		DriverManagerDataSource  dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public Connection jdbcConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}
}
