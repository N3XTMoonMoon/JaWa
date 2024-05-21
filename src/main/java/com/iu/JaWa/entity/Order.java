package com.iu.JaWa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="orders", schema="customer_order")
@SequenceGenerator(name = "order_id_seq", sequenceName = "customer_order.order_seq")
public class Order {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	private int ordId;
	
	private String status;
	
	@Column(name="order_user")
	private String orderUser;
	
	
//	@ManyToMany //This would work if no amount is needed
//	@JoinTable(
//			name="order_content", schema = "customer_order",
//			joinColumns = @JoinColumn(name="order_id"),
//			inverseJoinColumns = @JoinColumn(name="articleNumber"))
//	List<Item> orderContent;

	public Order() {
	}
	
	public Order(String status, String orderUser) {
		this.status = status;
		this.orderUser = orderUser;
	}
	
	public int getOrdId() {
		return ordId;
	}
	
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}
}
