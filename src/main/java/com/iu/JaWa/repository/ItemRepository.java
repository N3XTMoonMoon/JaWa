package com.iu.JaWa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
