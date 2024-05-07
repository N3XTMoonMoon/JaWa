package com.iu.JaWa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users", schema="users")
public class User {

	@Id
	@Column(name="user_name")
	private String userName;
	
	private int password;
	
	private String role;

	public User(String userName, int password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public User(String userName, int password) {
		this.userName = userName;
		this.password = password;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "UserName: "+userName+"; Role: "+role;//password is not printed for security reasons
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
