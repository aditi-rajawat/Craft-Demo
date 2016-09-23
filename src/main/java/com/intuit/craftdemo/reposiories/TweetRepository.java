package com.intuit.craftdemo.reposiories;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.Tweet;
/**
 * Created by aditi on 21/09/16.
 * MongoDB repository interface for "tweet" collection
 * "tweet" collection contains all the documents mapped to Tweet bean class
 */
@Service
public interface TweetRepository extends MongoRepository<Tweet, UUID> {
	
	public ArrayList<Tweet> findByAuthorName(String authorName);
	public ArrayList<Tweet> findFirst100ByAuthorNameInOrderByPublishDateDesc(ArrayList<String> authorName);
}
