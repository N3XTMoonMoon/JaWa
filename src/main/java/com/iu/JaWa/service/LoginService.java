package com.iu.JaWa.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.User;
import com.vaadin.flow.server.VaadinService;

import constants.LoginConstant;
import constants.UserRoleConstant;
import jakarta.servlet.http.Cookie;

@Service
public class LoginService {
	
	Logger log = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private UserService userService;
	

//	private void test() {
//		System.out.println(userService.getUserHash("admin"));
//		User test = new User("test","test".hashCode(),"USER");
//		System.out.println(userService.createNewUser(test).toString());
//	}
	
	//FIXME: fix bug, where comlete ui is visible after other user is logged in
	public void saveCookie(User loginUser) {
		
		Optional<User> usr = userService.findUser(loginUser.getUserName());
		
		if(usr.isPresent()) {
		
			Cookie deleteCookie = new Cookie("JaWa", UserRoleConstant.NOT_SET);
			// Make cookie expire in 20 minutes
			deleteCookie.setMaxAge(0);
			// Set the cookie path.
			deleteCookie.setPath(VaadinService.getCurrentRequest().getContextPath());
			// Save cookie
			VaadinService.getCurrentResponse().addCookie(deleteCookie);
			
			
			// Create a new cookie
			Cookie myCookie = new Cookie("JaWa", usr.get().getRole());
	
			// Make cookie expire in 20 minutes
			myCookie.setMaxAge(1200);
	
			// Set the cookie path.
			myCookie.setPath(VaadinService.getCurrentRequest().getContextPath());
	
			// Save cookie
			VaadinService.getCurrentResponse().addCookie(myCookie);
			log.info("Saved cookies for user: "+loginUser);
		}
	}
	
	//needs to be static for RouteTab initialization
	public static String getRoleFromLoggedinUser() {
		
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		
		for(Cookie currentCookie : cookies) {
			if(currentCookie.getName().equals("JaWa")) {
				return currentCookie.getValue();
			}
		}
		
		return UserRoleConstant.USER;
	}
	
	/**
	 * @return return "SUCCESS" or "FAILURE" depending on the fitting passwordhash
	 */
	public String login(String userName, int password) {
		
		int userPwd = userService.getUserHash(userName);
		
		if(userPwd == password) {
			return LoginConstant.SUCCESS;
		}else {
			return LoginConstant.FAILURE;
		}
	}
	
	/**
	 * checks if cookie "JaWa" is present
	 * @return true if it matches. false otherwise
	 */
	public boolean isLoggedIn() {
		
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		
		for(Cookie currentCookie : cookies) {
			if(currentCookie.getName().equals("JaWa")) {
				return true;
			}
		}
		//return after complete list is checked
		return false;
	}
	
	/**
	 * sets cookie "JaWa" to "FAILURE" if it was present
	 */
	public void logout() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		for(Cookie currentCookie : cookies) {
			if(currentCookie.getName().equals("JaWa")) {
				currentCookie.setValue(LoginConstant.FAILURE);
			}
		}
	}
}
