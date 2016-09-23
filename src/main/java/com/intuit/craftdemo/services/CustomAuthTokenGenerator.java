package com.intuit.craftdemo.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.WebToken;
import com.intuit.craftdemo.security.AuthTokenManager;

/**
 * Created by aditi on 21/09/16.
 * Service class for generating authorization token based on username and password
 */
@Service
public class CustomAuthTokenGenerator implements AuthTokenGenerator {
	
	@Autowired
	AuthTokenManager authTokenManager;
	
	public static final long TOKEN_EXPIRATION_MINUTES = 24*60;	// Expire after a day
	
	private Authentication getAuthentication(){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}
	
	@Override
	public String generateToken() {
		// TODO Auto-generated method stub
		final Authentication authentication = getAuthentication();
		WebToken webToken = new WebToken(authentication.getName(), authentication.getCredentials().toString());
		String customAuthToken = Base64.encodeBase64String(webToken.toString().getBytes());
		authTokenManager.saveToken(customAuthToken);
		return customAuthToken;
	}
}
