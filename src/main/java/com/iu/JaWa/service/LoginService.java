package com.iu.JaWa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.User;
import com.vaadin.flow.server.VaadinService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;

@Service
public class LoginService {

	@Autowired
	private UserService userService;
	
//	@PostConstruct
//	private void test() {
//		System.out.println(userService.getUserHash("admin"));
//		User test = new User("test","test".hashCode(),"USER");
//		System.out.println(userService.createNewUser(test).toString());
//	}
	
	public String login(String userName, int password) {
		
		int userPwd = userService.getUserHash(userName);
		
		if(userPwd == password) {
			return "SUCCESS";
		}else {
			return "FAILURE";
		}
	}
	
	public boolean isLoggedIn() {
		
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		
		for(Cookie currentCookie : cookies) {
			if(currentCookie.getName().equals("JaWa") && currentCookie.getValue().equals("SUCCESS")) {
				return true;
			}
		}
		//return after complete list is checked
		return false;
	}
	
	public void logout() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		for(Cookie currentCookie : cookies) {
			if(currentCookie.getName().equals("JaWa") && currentCookie.getValue().equals("SUCCESS")) {
				currentCookie.setValue("FAILURE");
			}
		}
	}
}
