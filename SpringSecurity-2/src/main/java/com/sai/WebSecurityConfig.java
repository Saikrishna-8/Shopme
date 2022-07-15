package com.sai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		UserDetails user1 = User
				.withUsername("sai")
				.password("$2a$10$PPh7y4wx0db28Bd/XjXW9u7PmBjeOyKM/ua2NFdHdJPTOmfNyn/Ii")
				.roles("USER")
				.build()
				;
		
		UserDetails user2=User
				.withUsername("admin")
				.password("$2a$10$PPh7y4wx0db28Bd/XjXW9u7PmBjeOyKM/ua2NFdHdJPTOmfNyn/Ii")
				.roles("ADMIN")
				.build()
				;
		
		return new InMemoryUserDetailsManager(user1,user2);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/","/home").permitAll()
			.mvcMatchers("/cpanel").hasRole("ADMIN")
			.anyRequest().authenticated().
		and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("u").passwordParameter("p")
				.permitAll()
		.and()
			.logout().permitAll();

	}

}
