package com.sai;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.inMemoryAuthentication()
		.passwordEncoder(new BCryptPasswordEncoder())
			.withUser("sai")
			.password("$2a$10$/pW8AGPlvg2KZJdqqkv2qeFgBeyX0R7ChCMkqQ8VzmMaoMDV73Zr2")
			.roles("USER")
		.and()
			.withUser("saiadmin")
			.password("$2a$10$/pW8AGPlvg2KZJdqqkv2qeFgBeyX0R7ChCMkqQ8VzmMaoMDV73Zr2")
			.roles("ADMIN")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/new").hasAnyRole("USER","ADMIN")
			.antMatchers("/edit/*","/delete/*").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and()
			.httpBasic()
		.and()
			.exceptionHandling().accessDeniedPage("/403")
		;
	}
	
}
