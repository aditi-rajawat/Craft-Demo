package com.intuit.craftdemo.beans;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Created by aditi on 21/09/16.
 * Tweet bean class holds the information related to a tweet or a message
 */
public class Tweet {
	
	@Id
	public UUID id;	// Unique identifier to be used for mongo repository
	@NotNull
    public String authorName;	// username of the author of the tweet
    public String publishDate;	// publish date of the tweet
    @NotNull
    public String message;	// message posted
    
    public Tweet(){}
    
	public Tweet(UUID id, String authorName, String publishDate, String message) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.publishDate = publishDate;
		this.message = message;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", authorName=" + authorName + ", publishDate=" + publishDate + ", message="
				+ message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
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
		Tweet other = (Tweet) obj;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		return true;
	}
    
}
