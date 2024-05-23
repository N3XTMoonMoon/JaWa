package com.iu.JaWa.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.Order;
import com.iu.JaWa.entity.OrderContent;
import com.iu.JaWa.model.OrderContentView;
import com.iu.JaWa.service.LoginService;
import com.iu.JaWa.service.OrderService;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@Route("/order")
@PageTitle("Bestellübersicht")
public class OrderUi extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 8543455234987098118L;
	Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private OrderService ordService;
	
	Grid<Order> orderGrid;
	List<Order> orderList;
	ListDataProvider<Order> orderDataProvider;
	
	//TODO: create new POJO for "view"
	Grid<OrderContentView> orderContentGrid;
	List<OrderContentView> orderContentList;
	ListDataProvider<OrderContentView> orderContentDataProvider;
	
	
	public OrderUi() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		orderGrid = new Grid<Order>();
		orderGrid.addColumn(Order::getOrdId).setHeader("Bestellnummer");
		orderGrid.addColumn(Order::getOrderUser).setHeader("Kunde"); //TODO: implement customer (User)
		orderGrid.addColumn(Order::getStatus).setHeader("Status");
//		orderGrid.addColumn(Order::getStatus).setHeader("Preis");
		orderGrid.setWidth("75%");
		
		orderGrid.addSelectionListener(e -> {
			Optional<Order> selected = e.getFirstSelectedItem();
			if(selected.isPresent()) {
				log.info("Bestellung wurde ausgewählt: "+ selected.get().getOrdId());
				fillOrderContent(selected.get().getOrdId());
			}
			
		});
		
		orderContentGrid = new Grid<OrderContentView>();
		orderContentGrid.addColumn(OrderContentView::getArticleNumber).setHeader("Artikelnummer");
		orderContentGrid.addColumn(OrderContentView::getArticleName).setHeader("Artikelname");
		orderContentGrid.addColumn(OrderContentView::getAmount).setHeader("Menge");
		orderContentGrid.addColumn(OrderContentView::getPrice).setHeader("Preis");
		orderContentGrid.setWidth("50%");
		
		Button inProgressBtn = new Button("In Bearbeitung");
		Button paidBtn = new Button("Bezahlt");
		Button sentBtn = new Button("Waare Versendet");
		
		VerticalLayout buttonVertLayout = new VerticalLayout();
		buttonVertLayout.add(inProgressBtn, paidBtn, sentBtn);
		
		
		Accordion acc = new Accordion();
		acc.add("Status anpassen", buttonVertLayout);
		
		HorizontalLayout horiOne = new HorizontalLayout();
		horiOne.setWidth("100%");
		
		VerticalLayout vertTwo = new VerticalLayout();
		
		H2 first = new H2("Offene Bestellungen");
		horiOne.add(first);
		horiOne.add(orderGrid, acc);
		
		vertTwo.add(first,horiOne );
		
		
		add(routeTabs,vertTwo,orderContentGrid);
	}
	
	private void fillOrderContent(int orderNumber) {
		orderContentList = ordService.getOrderContent(orderNumber);
		//refresh items according to selected order
		orderContentDataProvider.getItems().clear();
		orderContentDataProvider.getItems().addAll(orderContentList);
		orderContentDataProvider.refreshAll();
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			//redirect
			event.rerouteTo(LoginUi.class);
		}
		
		orderList = ordService.getAllOpenOrders();
		orderDataProvider = new ListDataProvider<Order>(orderList);
		orderGrid.setDataProvider(orderDataProvider);
		
		orderContentList = new ArrayList<OrderContentView>();
		orderContentDataProvider = new ListDataProvider<OrderContentView>(orderContentList);
		orderContentGrid.setDataProvider(orderContentDataProvider);
		
	}
}
