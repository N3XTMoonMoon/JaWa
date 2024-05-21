package com.iu.JaWa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.JaWa.entity.CurrentStock;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.entity.Order;
import com.iu.JaWa.entity.OrderContent;
import com.iu.JaWa.repository.OrderContentRepository;
import com.iu.JaWa.repository.OrderRepository;

import constants.OrderConstant;

@Service
public class OrderService {

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
}
