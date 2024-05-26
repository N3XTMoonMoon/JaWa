package com.iu.JaWa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.OrderContent;

public interface OrderContentRepository extends JpaRepository<OrderContent, Integer>{

	List<OrderContent> findByOrderId(int orderNumber);
	
}