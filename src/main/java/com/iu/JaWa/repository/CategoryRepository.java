package com.iu.JaWa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Optional<Category> findByName(String name);

}
