package com.iu.JaWa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
