package com.intuit.craftdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
/**
 * Created by aditi on 21/09/16.
 * Configuration class for security of the RESTful APIs
 * Only login API is authenticated using LDAP authentication. On successful authentication,
 * an authorization token is generated for the user.
 * The authorization token must be used in HTTP header of other APIs that are authorized using HTTP Basic Authentication 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthTokenManager authTokenManager;
	
	public static final String USER_LOGIN_PATH="/user/login";
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.addFilterBefore(new CustomChannelProcessingFilter(authTokenManager), AnonymousAuthenticationFilter.class)
			.authorizeRequests()
			.regexMatchers(USER_LOGIN_PATH).fullyAuthenticated()
			.and().httpBasic()
			.and().csrf().disable()
			.sessionManagement().disable();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder)
		throws Exception{
		authManagerBuilder
			.authenticationProvider(new LdapAuthenticationProvider()).eraseCredentials(false);
	}
}
