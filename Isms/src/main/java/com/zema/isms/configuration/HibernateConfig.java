/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.configuration;



import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

/**a
 *
 * @author ewawuye
 */
@Configuration

@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScan(basePackages ={"com.zema.isms.dao","com.zema.isms.service"})
public class HibernateConfig {
   @Autowired
	private Environment environment;

      @Bean
    public LocalSessionFactoryBean getSessionFactory() {
          final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
       
        sessionFactory.setPackagesToScan(new String[] { "com.zema.isms.domain" });

        sessionFactory.setHibernateProperties(hibernateProperties());
        
          return sessionFactory;
      
    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource ds = new  BasicDataSource();
              ds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("jdbc.url"));
		ds.setUsername(environment.getRequiredProperty("jdbc.username"));
		ds.setPassword(environment.getRequiredProperty("jdbc.password"));
		return ds;
         
        
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
       properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
           properties.put("hibernate.connection.characterEncoding",environment.getRequiredProperty("hibernate.connection.characterEncoding"));
           properties.put("hibernate.connection.charSet", environment.getRequiredProperty("hibernate.connection.charSet"));
             properties.put("hibernate.connection.useUnicode", environment.getRequiredProperty("hibernate.connection.useUnicode"));

        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
      final  HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    } 
     
}
