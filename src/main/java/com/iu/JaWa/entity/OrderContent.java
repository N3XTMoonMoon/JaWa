package com.iu.JaWa.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(OrderContent.class)
@Table(name="order_content", schema="customer_order")
public class OrderContent {

	@Id
	@Column(name="order_id")
	private int orderId;
	
	private int amount;
		
	@Column(name="article_number")
	private int articleNumber;
	
		
//	@ManyToOne(cascade = CascadeType.PERSIST) //This would work if amount is not needed
//	@MapsId("articleNumber")
//	@JoinColumn(name="article_Number")
//	private Item article;
	
	public void setAmount(int amount) {
		this.amount=amount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, articleNumber, orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderContent other = (OrderContent) obj;
		return amount == other.amount && articleNumber == other.articleNumber && orderId == other.orderId;
	}
	
	
}
