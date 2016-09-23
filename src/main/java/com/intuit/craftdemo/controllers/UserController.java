package com.intuit.craftdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craftdemo.beans.WebToken;
import com.intuit.craftdemo.services.AuthTokenGenerator;
import com.intuit.craftdemo.services.CustomAuthTokenGenerator;
/**
 * Created by aditi on 21/09/16.
 * Controller class for user management
 * Used to generate authorization token on successful login
 */
@RestController
public class UserController {
	
	@Autowired
	AuthTokenGenerator AuthTokenGenerator = new CustomAuthTokenGenerator();

	@RequestMapping(value = "/user/login", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> generateAuthToken(){
		return new ResponseEntity<String>(AuthTokenGenerator.generateToken(), HttpStatus.OK);
	}
}
