package com.intuit.craftdemo.controllers;

import com.intuit.craftdemo.beans.Tweet;
import com.intuit.craftdemo.services.CustomTweetService;
import com.intuit.craftdemo.services.TweetService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

/**
 * Created by aditi on 21/09/16.
 * Controller class for handling all the requests related to a tweet or a post
 */

@RestController
public class TweetController {
	
	@Autowired
	TweetService tweetService;
	
	@RequestMapping(value = "/tweet", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Tweet> postTweet(@Valid @RequestBody Tweet tweet, BindingResult bindingResult)
		throws BadRequestException, InternalServerErrorException{
		
		if(bindingResult.hasErrors()){
			throw new BadRequestException("Author name or the message is/are missing in the request");
		}
		
		try{
			Tweet postedTweet = tweetService.postTweet(tweet);
			return new ResponseEntity<Tweet>(postedTweet, HttpStatus.CREATED);
		}catch (Exception ex) {
			throw new InternalServerErrorException(ex.getMessage());
		}
	}

    @RequestMapping(value = "/feed", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<Tweet>> getLatestTweets()
    	throws InternalServerErrorException{
    	
    	try{
	        ArrayList<Tweet> listOfLatestTweets = new ArrayList<>();
	        listOfLatestTweets = tweetService.getLatestTweets();
	
	        if(listOfLatestTweets!=null && !listOfLatestTweets.isEmpty()){
	            return new ResponseEntity<ArrayList<Tweet>>(listOfLatestTweets, HttpStatus.OK);
	        }
	        else{
	            return new ResponseEntity<ArrayList<Tweet>>(HttpStatus.NO_CONTENT);
	        }
    	}catch (Exception ex) {
			// TODO: handle exception
    		throw new InternalServerErrorException(ex.getMessage());
		}
    }
}
