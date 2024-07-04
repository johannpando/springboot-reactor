package com.johannpando.springboot.reactor.app.models;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	
	List<String> commentList;

	public Comment() {
		this.commentList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Comments [commentList=" + commentList + "]";
	}
	
	public void addComment(String comment){
		this.commentList.add(comment);
	}

}
