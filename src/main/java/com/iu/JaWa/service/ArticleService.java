package com.iu.JaWa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.Item;
import com.iu.JaWa.repository.ItemRepository;

@Service
public class ArticleService {

	@Autowired
	ItemRepository itemRepo;
	
	public Item saveArticleToDb(Item item) {
		
		return itemRepo.save(item);
	}

	public List<Item> getAllItems() {
		List<Item> itemList = itemRepo.findAll();
		
		return itemList;
	}
}
