package com.iu.JaWa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@IdClass(CurrentStockId.class)
@Table(name="current_stock", schema="article")
public class CurrentStock {

	@Id
	@Column(name="article_number")
	private int articleNumber;
	
	private String name;
	
	private int amount;
	
	private double price;
	
	@Id
	private LocalDate mhd;
	
	@ManyToOne
	@MapsId
	@JoinColumn(name="article_number")
	//, referencedColumnName = "article_number")
	private Item article;
	
	public CurrentStock() {
		
	}

	public CurrentStock(String name, int amount, double price, LocalDate mhd) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.mhd = mhd;
	}
	
	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDate getMhd() {
		return mhd;
	}

	public void setMhd(LocalDate mhd) {
		this.mhd = mhd;
	}
	
	public String getCategory() {
		//hope this never crashes lol
		return this.article.getCategory().getName();
	}
}
