package com.iu.JaWa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.Category;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.repository.CategoryRepository;
import com.iu.JaWa.repository.ItemRepository;

@Service
public class test {

	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	CategoryRepository catrepo;
	

	public void testMethods() {
		fetchItems();
		fetchCategories();
	}
	
	public void fetchItems() {
		Optional<Item> item = itemRepo.findById(10001);
		
		System.out.println(item.get().toString());
	}
	
	public void fetchCategories() {
		Optional<Category> cat = catrepo.findById(1);
		
		System.out.println(cat.get().toString());
		
		
	}
	
}
