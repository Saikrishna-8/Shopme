package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http .csrf().disable() .authorizeRequests().antMatchers("/login").permitAll()
		 * .anyRequest().authenticated();
		 */

		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().oauth2Login();
	}

}
