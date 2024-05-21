package com.iu.JaWa.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderContentId implements Serializable{
	private static final long serialVersionUID = 3786598858206592244L;

	private int orderId;
	
	private int amount;
	
	private int articleNumber;

	public OrderContentId() {
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
		OrderContentId other = (OrderContentId) obj;
		return amount == other.amount && articleNumber == other.articleNumber && orderId == other.orderId;
	}

	
}