package com.intuit.craftdemo.services;

import org.springframework.stereotype.Service;
/**
 * Created by aditi on 21/09/16.
 * Interface for services related to generation of authorization token
 */
@Service
public interface AuthTokenGenerator {
	public String generateToken();
}
