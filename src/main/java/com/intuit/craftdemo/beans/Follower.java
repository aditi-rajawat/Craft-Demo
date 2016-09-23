package com.intuit.craftdemo.beans;

import java.util.ArrayList;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by aditi on 21/09/16.
 * Follower bean class holds the information about a user(follower) and the list of other users(followees) whom 
 * he is following
 */

public class Follower {

	@Id
	public UUID id;	// unique identifier for the mongo repository
	@NotNull
    public String name;	// username of the user or the follower
    public ArrayList<String> following = new ArrayList<String>();	// list of usernames of followees
    
    public Follower(){}

	public Follower(UUID id, String name, ArrayList<String> following) {
		super();
		this.id = id;
		this.name = name;
		this.following = following;
	}

	@Override
	public String toString() {
		return "Follower [id=" + id + ", name=" + name + ", following=" + following + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getFollowing() {
		return following;
	}

	public void setFollowing(ArrayList<String> following) {
		this.following = following;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((following == null) ? 0 : following.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Follower other = (Follower) obj;
		if (following == null) {
			if (other.following != null)
				return false;
		} else if (!following.equals(other.following))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
}
