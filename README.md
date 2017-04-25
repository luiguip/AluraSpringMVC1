# AluraSpringMVC1

JPAConfiguration removed for security reasons. Here the code without the database information

JPAConfiguration.java

package org.casadocodigo.store.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("**********");
		dataSource.setPassword("**********");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(properties);
		
		factoryBean.setPackagesToScan("org.casadocodigo.store.models");
		
		return factoryBean;

	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
		
	}
}

