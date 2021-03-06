package com.taskmanagementapp.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.taskmanagementapp.web.common.Constants;

/**
 * Spring Security Configuration
 * 
 * @author Ayush
 * @version 0.1, 25 March 2022
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Password Encoder configuration
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * userDetailsService configuration with security
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	/**
	 * Authentication And Authorization configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/task/**").authenticated().and().formLogin()
				.loginPage(Constants.LOGIN_URL).loginProcessingUrl(Constants.LOGIN_URL).usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/task/").permitAll().and().exceptionHandling()
				.accessDeniedPage(Constants.LOGIN_URL).and().sessionManagement().maximumSessions(1);

		http.logout(
				logout -> logout.logoutUrl("/logout").permitAll().addLogoutHandler(new SecurityContextLogoutHandler()));
	}

}
