package com.johannpando.springboot.reactor.app.models;

public class UserWithComments {
	
	private User user;
	
	private Comment comment;

	public UserWithComments(User user, Comment comment) {
		super();
		this.user = user;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "UserWithComments [user=" + user + ", comment=" + comment + "]";
	}
	
	

}
