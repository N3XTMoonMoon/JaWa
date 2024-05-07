package com.iu.JaWa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.Categorie;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.repository.CategoryRepository;
import com.iu.JaWa.repository.ItemRepository;

@Service
public class ArticleService {

	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	public Item saveArticleToDb(Item item) {
		
		return itemRepo.save(item);
	}

	public List<Item> getAllItems() {
		List<Item> itemList = itemRepo.findAll();
		
		return itemList;
	}

	public List<Categorie> getAllCategories() {
		
		
		//filter placehoder category
		return catRepo.findAll().stream().filter(e -> !e.equals(new Categorie(0, "MISSING"))).collect(Collectors.toList());
	}
	
}
