package com.intuit.craftdemo.services;

import java.util.ArrayList;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.Follower;
import com.intuit.craftdemo.beans.Tweet;
import com.intuit.craftdemo.reposiories.FollowerRepository;
import com.intuit.craftdemo.reposiories.TweetRepository;
/**
 * Created by aditi on 21/09/16.
 * Service class for fetching or modifying data stored in mongo repositories related to tweets or posts
 */
@Service
public class CustomTweetService implements TweetService {
	
	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	FollowerService followerService;
	
	private String getFollowerName(){
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		final Authentication authentication = securityContext.getAuthentication();
		if(authentication!=null)
			return authentication.getName();
		else
			return null;
	}
	
	@Override
	public Tweet postTweet(Tweet tweet) {
		// TODO Auto-generated method stub
		tweet.setId(UUID.randomUUID());
		DateTime today = new DateTime(DateTimeZone.UTC);
		tweet.setPublishDate(today.toString(ISODateTimeFormat.dateTime().withZoneUTC()));
		return tweetRepository.save(tweet);
	}

	@Override
	public ArrayList<Tweet> getLatestTweets() {
		// TODO Auto-generated method stub
		String followerName = getFollowerName();
		ArrayList<Tweet> latestTweets = new ArrayList<>();
		ArrayList<String> listOfFollowing = followerService.getListOfFollowing(followerName);
		if(listOfFollowing==null){
			listOfFollowing = new ArrayList<>();
		}
		listOfFollowing.add(followerName);
		latestTweets = tweetRepository.findFirst100ByAuthorNameInOrderByPublishDateDesc(listOfFollowing);
		return latestTweets;
	}
}
