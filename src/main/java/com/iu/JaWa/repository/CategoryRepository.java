package com.iu.JaWa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.Categorie;

public interface CategoryRepository extends JpaRepository<Categorie, Integer>{
	
	public Optional<Categorie> findByName(String name);

}
