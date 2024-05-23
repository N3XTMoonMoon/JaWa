package com.iu.JaWa.model;

import java.util.Objects;

public class OrderContentView {

	int articleNumber;
	String articleName;
	int amount;
	double price;
	
	public OrderContentView(int articleNumber, String articleName, int amount, double price) {
		this.articleNumber = articleNumber;
		this.articleName = articleName;
		this.amount = amount;
		this.price = price;
	}
	
	public int getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, articleName, articleNumber, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderContentView other = (OrderContentView) obj;
		return amount == other.amount && Objects.equals(articleName, other.articleName)
				&& articleNumber == other.articleNumber
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	
	
}
