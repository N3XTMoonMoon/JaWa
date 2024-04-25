package com.iu.JaWa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.User;
import com.iu.JaWa.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepo;
	
	@PostConstruct
	private void test() {
		System.out.println(getUserHash("admin"));
		User test = new User("test","test".hashCode(),"USER");
		System.out.println(createNewUser(test).toString());
	}
	
	/**
	 * searches for given userName on "user.users" table
	 * 
	 * @param username
	 * @return the saved hash value of the entered password
	 */
	public int getUserHash(String username) {
		Optional<User> result = userRepo.findById(username);
		
		//check if userName is inside the table
		if(result.isPresent()) {
			return result.get().getPassword();
		} else {
			return -1;
		}
	}
	
	public User createNewUser(User newUser) {
		
		int hash = getUserHash(newUser.getUserName());
		
		if(hash>0) {
			//user ist already added
			return newUser;
		}else {
			return userRepo.saveAndFlush(newUser);
		}
	}
	
	public void removeUser (User removeUser) {
		if(removeUser==null) {
			throw new NullPointerException();
		}
		
		userRepo.delete(removeUser);
	}
}
