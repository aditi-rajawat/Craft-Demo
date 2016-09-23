package com.intuit.craftdemo.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craftdemo.beans.Follower;
import com.intuit.craftdemo.reposiories.FollowerRepository;
/**
 * Created by aditi on 21/09/16.
 * Custom service class implementing FollowerService
 * Used to query data stored in mongo repositories
 */
@Service
public class CustomFollowerService implements FollowerService {
	
	@Autowired
	FollowerRepository followerRepository;
	
	@Override
	public Follower updateFollower(Follower follower) {
		Follower oldRecord = followerRepository.findByName(follower.getName());
		if(oldRecord==null){
			follower.setId(UUID.randomUUID());
			return followerRepository.save(follower);
		}
		else{
			oldRecord.setFollowing(follower.getFollowing());
			return followerRepository.save(oldRecord);
		}
	}

	@Override
	public ArrayList<String> getListOfFollowing(String followerName) {
		Follower follower = followerRepository.findByName(followerName);
		if(follower!=null)
			return follower.getFollowing();
		else
			return null;
	}
}
