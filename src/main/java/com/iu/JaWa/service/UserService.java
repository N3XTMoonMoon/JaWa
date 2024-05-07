package com.iu.JaWa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.User;
import com.iu.JaWa.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public UserService() {
		
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
	
	public Optional<User> findUser(String userName) {
		return userRepo.findById(userName);
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

	public List<User> getAllUser() {
		return userRepo.findAll();
	}
}
