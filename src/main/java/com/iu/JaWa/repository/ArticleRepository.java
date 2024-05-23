package com.iu.JaWa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.Item;

public interface ArticleRepository extends JpaRepository<Item, Integer>{

}
