package com.iu.JaWa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="orders", schema="customer_order")
@SequenceGenerator(name = "order_id_seq", sequenceName = "customer_order.order_seq", allocationSize = 1)
public class Order {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	private int ordId;
	
	private String status;
	
	@Column(name="order_user")
	private String orderUser;
	
	@Transient
	int totalPrice;
		
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

	@Override
	public int hashCode() {
		return Objects.hash(ordId, orderUser, status, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return ordId == other.ordId && Objects.equals(orderUser, other.orderUser)
				&& Objects.equals(status, other.status) && totalPrice == other.totalPrice;
	}
}
