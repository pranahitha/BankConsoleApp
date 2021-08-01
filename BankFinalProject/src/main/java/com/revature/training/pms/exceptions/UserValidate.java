package com.revature.training.pms.exceptions;

import java.util.Scanner;

public class UserValidate {
	protected int userId;
	
	protected String username;
	protected String password;
	public UserValidate()
	{
		
	}
	
	
	public UserValidate(int userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



}

