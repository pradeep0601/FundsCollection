package com.aricent.fundscollection.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public User() {
	System.out.println("User()");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

	
}
