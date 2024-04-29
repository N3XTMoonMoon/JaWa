package com.iu.JaWa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name="articles",schema="article")
public class Item {

	@Id
	@Column(name="article_number")
	@SequenceGenerator(name = "some_seq", sequenceName = "article.articlenumber_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_seq")
	private int articleNumber;
	
	@Column(name="category",insertable=false, updatable=false)
	private int categoryId;
	
	private String name;
	
	private String description;
	
	private double price;
	
	private int stock;
	
	@ManyToOne//(optional=false)
	@JoinColumn(name="category",referencedColumnName="cat_id")
	private Categorie category;
	
	public Item(Categorie category, String name, String description, double price, int stock) {
		this.categoryId = category.getId();
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Item(int articleNumber, Categorie category, String name, String description, double price, int stock) {
		super();
		this.articleNumber = articleNumber;
		this.categoryId = category.getId();
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Item() {
		super();
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Categorie getCategory() {
		return category;
	}

	public void setCategory(Categorie category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
