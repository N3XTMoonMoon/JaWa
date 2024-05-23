package com.iu.JaWa.model;

public class CookieUserValue {

	private String userName;
	private String loginStatus;
	private String role;
	
	public CookieUserValue(String userName, String loginStatus, String role) {
		super();
		this.userName = userName;
		this.loginStatus = loginStatus;
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
