/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zema.isms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author ewawuye
 */
@Configuration
@EnableWebSecurity
public class AmsSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailService")
	private UserDetailsService amsUserDetailsService;
    	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(amsUserDetailsService);
		auth.authenticationProvider(authenticationProvider());
                        }
        
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(amsUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
   @Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
			    .antMatchers("/asset/**","/report/**","/security/**").permitAll()
                             .antMatchers("/request/request_list").hasAnyAuthority("DIRECTER")
                              .antMatchers("/catagory/**","/dept/**","/organization/**").hasAnyAuthority("ADMIN")
                  
                             .antMatchers("/request/requestaApproveManager").hasAnyAuthority("MANAGER")
                                .anyRequest().authenticated()
                               
                                
                     	.and()
				.formLogin()
					.loginPage("/loginPage").loginProcessingUrl("/login").permitAll()
					
                       
			.and()
				.logout().permitAll()
			.and()
			   .exceptionHandling().accessDeniedPage("/access-denied")
                   
                .and()
                     .csrf().disable();
                        
	}
  
        /**
	 * Configure Web permissions (images, css, js, etc.)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
		.antMatchers("/resource/**", "/static/**", "/css/**", "/image/**", "/js/**");
		
	}
}
