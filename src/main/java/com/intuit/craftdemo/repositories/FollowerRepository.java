package com.intuit.craftdemo.repositories;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.intuit.craftdemo.beans.Follower;
/**
 * Created by aditi on 21/09/16.
 * MongoDB repository interface for "follower" collection
 * "follower" collection contains all the documents mapped to Follower bean class
 */
@Repository
public interface FollowerRepository extends MongoRepository<Follower, UUID> {
	
	public Follower findByName(String name);
	
}
