package com.iu.JaWa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	
}
