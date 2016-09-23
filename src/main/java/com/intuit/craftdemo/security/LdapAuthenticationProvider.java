package com.intuit.craftdemo.security;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
/**
 * Created by aditi on 21/09/16.
 * Authentication provider class that authenticates a user using LDAP service
 */
public class LdapAuthenticationProvider implements AuthenticationProvider {
	
	private HashMap<String, String> userCredentials = new HashMap<>();
	
	public LdapAuthenticationProvider(){
		initializeUserCredentials();	// Actual implementation must authenticate the user with LDAP
	}
	
	private void initializeUserCredentials(){
		userCredentials.put("admin", "admin123");
		userCredentials.put("arajawat", "aditi_91");
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		if(userName!=null && password!=null){
			if(!password.equals(this.userCredentials.get(userName)))
				throw new BadCredentialsException("Username or password did not match");
		}
		else{
			throw new BadCredentialsException("Username or password did not match");
		}
		
		Authentication token = new UsernamePasswordAuthenticationToken(userName, password);		
		return token;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
