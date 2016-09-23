package com.intuit.craftdemo.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.Follower;
/**
 * Created by aditi on 21/09/16.
 * FollowerService is an interface of services related to modification of data of a follower
 */
@Service
public interface FollowerService {
	
	public Follower updateFollower(Follower follower);
	public ArrayList<String> getListOfFollowing(String followerName);
	 
}
