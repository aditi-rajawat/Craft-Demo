package com.intuit.craftdemo.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.Tweet;
/**
 * Created by aditi on 21/09/16.
 * Interface for all the services used to modify or fetch data related to tweets or posts
 */
@Service
public interface TweetService {
	
	public Tweet postTweet(Tweet tweet);
	public ArrayList<Tweet> getLatestTweets();
}
