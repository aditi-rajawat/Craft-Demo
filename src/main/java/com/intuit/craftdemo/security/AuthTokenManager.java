package com.intuit.craftdemo.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intuit.craftdemo.services.CustomAuthTokenGenerator;
/**
 * Created by aditi on 21/09/16.
 * Used for persisting generated tokens in local cache. Manager class for saving and verifying tokens.
 * Tokens are expired after a fixed time period
 */
@Configuration
public class AuthTokenManager{
	
	private static final int CACHE_SIZE = 10000;
	private static final long TOKEN_EXPIRATION_MINUTES = CustomAuthTokenGenerator.TOKEN_EXPIRATION_MINUTES;
	private Cache<String, Authentication> tokenCache;
	
	@Autowired
	public AuthTokenManager(@Value("1000") int cacheSize) {
		// TODO Auto-generated constructor stub
		cacheSize = CACHE_SIZE;
		initialiseTokenCache(cacheSize);
	}
	
	public void initialiseTokenCache(int cacheSize) {
		// TODO Auto-generated method stub
		tokenCache = CacheBuilder.newBuilder()
				.maximumSize(cacheSize)
				.expireAfterWrite(TOKEN_EXPIRATION_MINUTES, TimeUnit.MINUTES)
				.build();
	}
	
	private Authentication getAuthentication(){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}
	
	private void setAuthentication(Authentication authentication){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
	}
	
	public void saveToken(String token) {
		// TODO Auto-generated method stub
		final Authentication authentication = getAuthentication();
		tokenCache.put(token, authentication);
	}
	
	public boolean verifyToken(String token) {
		// TODO Auto-generated method stub
		final Authentication authentication = tokenCache.getIfPresent(token);
		if(authentication!=null){
			setAuthentication(authentication);
		}
		return authentication!=null;
	}

}
