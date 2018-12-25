package com.tishkevich.spring.configuration;

import com.tishkevich.spring.service.UserDetailsServiceImpl;
import com.tishkevich.spring.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsServiceImpl userDetailsService;

	@Autowired
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/hello", "/api/getRandomQuestions", "/api/check", "/api/getAllCategories", "/api/getRandomQuestions/*").permitAll();
		http.authorizeRequests().antMatchers("/api/check").access("hasAnyRole(\"ROLE_USER\", \"ROLE_ADMIN\")");
		http.authorizeRequests().antMatchers("/api/getAllQuestions", "/api/delete/*", "/api/add").access("hasRole(\"ROLE_ADMIN\")");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
				http.authorizeRequests().and().formLogin().loginPage("/login").defaultSuccessUrl("/redirect/new").failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password").and().logout().permitAll();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(EncrytedPasswordUtils.getPasswordEncoder());

	}

}