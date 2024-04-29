package com.iu.JaWa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category",schema="article")
public class Categorie {

	@Id
	@Column(name="cat_id")
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="category")
	private List<Item> articleList;

	public Categorie() {
	}

	public Categorie(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//write to display corect values
	@Override
	public String toString() {
		return name;
	}
}
