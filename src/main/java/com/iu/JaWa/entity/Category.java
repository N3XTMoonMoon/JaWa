package com.iu.JaWa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="category",schema="article")
@SequenceGenerator(name = "some_seq", sequenceName = "article.category_seq")
public class Category {

	@Id
	@Column(name="cat_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_seq")
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="category")
	private List<Item> articleList;

	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
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

	//Manetory for using the select component
	@Override
	public boolean equals(Object cat) {
		if(cat instanceof Category) {
			return this.name.equals(((Category) cat).getName());
		}else {
			return false;
		}
		
	}
	
	//relevant for setValue Method from select
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
