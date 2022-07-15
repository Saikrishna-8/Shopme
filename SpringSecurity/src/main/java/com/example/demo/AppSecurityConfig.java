package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		// provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/loginurl", "/").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/loginurl").defaultSuccessUrl("/home", true).permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logouturl").permitAll();

		/*
		 * http .authorizeRequests().antMatchers("/first").hasRole("SECURE_USERS")
		 * .antMatchers("/second").hasRole("SECURE_USERS") .antMatchers("/").permitAll()
		 * .anyRequest().authenticated() .and() .formLogin()
		 * .loginPage("/loginurl").permitAll().and().logout().permitAll();
		 * http.exceptionHandling().accessDeniedPage("/403");
		 */
	}

	// @Bean
	// @Override
	// protected UserDetailsService userDetailsService() {

	// List<UserDetails> users = new ArrayList<UserDetails>();
	// users.add(User.withDefaultPasswordEncoder().username("sai").password("samantha").roles("USER").build());

	// return new InMemoryUserDetailsManager(users);

	// }

}
