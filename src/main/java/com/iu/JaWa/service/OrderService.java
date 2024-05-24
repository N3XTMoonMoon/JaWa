package com.iu.JaWa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.CurrentStock;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.entity.Order;
import com.iu.JaWa.entity.OrderContent;
import com.iu.JaWa.model.OrderContentView;
import com.iu.JaWa.repository.ArticleRepository;
import com.iu.JaWa.repository.OrderContentRepository;
import com.iu.JaWa.repository.OrderRepository;

import constants.OrderConstant;

@Service
public class OrderService {
	
	@Autowired
	ArticleRepository artRepo;

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderContentRepository orderContentRepo;
	
	public void saveOrder(List<CurrentStock> cartArticles, String user) {
		Order order = new Order();
		order.setOrderUser(user);
		order.setStatus(OrderConstant.OPEN);
		order = orderRepo.save(order);
		
		for(CurrentStock curr : cartArticles) {
			
			Item item = curr.getArticle();
			
			OrderContent product = new OrderContent();
			
			//link order id to order content
			product.setOrderId(order.getOrdId());
			product.setArticleNumber(item.getArticleNumber());
						
			product.setAmount(curr.getAmount());
			
			orderContentRepo.saveAndFlush(product);
			
		}
	}

	public List<Order> getAllOpenOrders() {
		return orderRepo.findByStatus(OrderConstant.OPEN);
	}

	public List<OrderContentView> getOrderContent(int orderNumber) {
		
		List<OrderContentView> orderContentList = new ArrayList<OrderContentView>();
		
		List<OrderContent> orders = orderContentRepo.findByOrderId(orderNumber);
		
		for(OrderContent cont : orders) {
			Optional<Item> item = artRepo.findById(cont.getArticleNumber());
			if(item.isPresent()) {
				
				orderContentList.add(new OrderContentView(cont.getOrderId(), item.get().getName(), cont.getAmount(), item.get().getPrice()));
			}
		}
		
		return orderContentList;
	}

	public void setOrderStatus(Order order, String newStatus) {
		if(order != null) {
			order.setStatus(newStatus);
			orderRepo.save(order);
		}
	}

	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
}
