package com.cms.domains;

import java.util.ArrayList;

public class User {

	private String userID;
	private String userName;
	private String password;
	private String lastName;
	private String firstName;
	private ArrayList<Module> listModule;
	private String stamp;
	
	public User() {
		this.userID = "";
		this.userName = "";
		this.password = "";
		this.lastName = "";
		this.firstName = "";
		this.listModule = new ArrayList<Module>();
		this.stamp = "";
	}
	
	public User(String userID, String userName, String password, String lastName, String firstName, 
			ArrayList<Module> listModule, String stamp) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.listModule = listModule;
		this.stamp = stamp;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public ArrayList<Module> getListModule() {
		return listModule;
	}

	public void setListModule(ArrayList<Module> listModule) {
		this.listModule = listModule;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getStamp() {
		return stamp;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}


}
