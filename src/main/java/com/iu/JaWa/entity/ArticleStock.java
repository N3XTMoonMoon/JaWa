package com.iu.JaWa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="article_stock",schema="article")
@SequenceGenerator(name = "some_seq", sequenceName = "article.stock_seq", allocationSize = 1)
public class ArticleStock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_seq")
	private int id;
	
	@Column(name="article_id")
	private int articleId;
	
	private int amount;
	
	//TODO: remove packaging
	private String packaging;
	
	private LocalDate mhd;

	public ArticleStock(int id, int articleId, int amount, String packaging, LocalDate mhd) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.amount = amount;
		this.packaging = packaging;
		this.mhd = mhd;
	}
	
	//TODO: remove packaging
	public ArticleStock(int articleId, int amount, String packaging, LocalDate mhd) {
		this.articleId = articleId;
		this.amount = amount;
		this.packaging = packaging;
		this.mhd = mhd;
	}
	
	public ArticleStock(int articleId, int amount, LocalDate mhd) {
		this.articleId = articleId;
		this.amount = amount;
		this.mhd = mhd;
	}
	
	public ArticleStock() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public LocalDate getMhd() {
		return mhd;
	}
	public void setMhd(LocalDate mhd) {
		this.mhd = mhd;
	}
}
