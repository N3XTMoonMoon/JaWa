package com.iu.JaWa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users", schema="user")
public class user {

	@Id
	@Column(name="user_name")
	private String userName;
	
	private int password;
	
	private String role;

	public user(String userName, int password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public user() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
