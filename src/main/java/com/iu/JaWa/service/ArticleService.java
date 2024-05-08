package com.iu.JaWa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.ArticleStock;
import com.iu.JaWa.entity.Category;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.repository.ArticleStockRepository;
import com.iu.JaWa.repository.CategoryRepository;
import com.iu.JaWa.repository.ItemRepository;

@Service
public class ArticleService {

	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ArticleStockRepository stockRepo;
	
	public Item saveArticleToDb(Item item) {
		
		return itemRepo.save(item);
	}

	public List<Item> getAllItems() {
		List<Item> itemList = itemRepo.findAll();
		
		return itemList;
	}

	public List<Category> getAllCategories() {
		
		
		//filter placehoder category
		return catRepo.findAll().stream().filter(e -> !e.equals(new Category(0, "MISSING"))).collect(Collectors.toList());
	}

	public void saveCategory(String value) {
		
		Optional<Category> catResult = catRepo.findByName(value);

		if(!catResult.isPresent()) {
			catRepo.save(new Category(value));
		}
	}

	public void deleteCategory(Category categorie) {
		catRepo.delete(categorie);
	}

	public List<ArticleStock> getAllItemsInStock() {
		
		return stockRepo.findAll();
	}

	public void addToStock(Item item, int amount, LocalDate mhd) {
		//check if item is already inside table
		
		Optional<ArticleStock> result = stockRepo.findByArticleIdAndMhd(item.getArticleNumber(), mhd);
		
		if(result.isPresent()) {
			ArticleStock currentStock = result.get();
			
			currentStock.setAmount(currentStock.getAmount()+amount);//Add amount to already stock if artNr fit and same mhd
			
			stockRepo.save(currentStock);
		}else {
			
			//article not found in stock with same artNr and same mhd
			ArticleStock newStock = new ArticleStock(item.getArticleNumber(), amount, mhd);
			stockRepo.save(newStock);
		}
	}
}