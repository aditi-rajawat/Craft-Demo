package com.intuit.craftdemo.controllers;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craftdemo.beans.Follower;
import com.intuit.craftdemo.services.FollowerService;
/**
 * Created by aditi on 21/09/16.
 * Controller class for handling all the requests related to modification of the following list of a follower or user
 */
@RestController
public class FollowerController {
	
	@Autowired
	FollowerService followerService;
		
	@RequestMapping(value = "/follower", method = RequestMethod.PUT, produces= "application/json")
	public ResponseEntity<Follower> addToFollowingList(@Valid @RequestBody Follower follower, BindingResult bindingResult)
		throws BadRequestException, InternalServerErrorException{
		
		if(bindingResult.hasErrors())
			throw new BadRequestException("Follower name missing in the request");
		try{
			Follower updatedfollower = followerService.updateFollower(follower);
			return new ResponseEntity<Follower>(updatedfollower, HttpStatus.OK);
		}catch(Exception ex){
			throw new InternalServerErrorException(ex.getMessage());
		}
	}

}
