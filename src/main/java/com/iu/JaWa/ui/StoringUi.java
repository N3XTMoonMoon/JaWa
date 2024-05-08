package com.iu.JaWa.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.ArticleStock;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@Route("/store")
@PageTitle("Lagerverwaltung")
public class StoringUi extends VerticalLayout implements BeforeEnterObserver{
	private static final long serialVersionUID = 2045973054759982104L;
	Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ArticleService artService;

	Grid<ArticleStock> grid;
	Select<Item> availableItems;
	
	public StoringUi() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		grid = new Grid<ArticleStock>();
		grid.addColumn(ArticleStock::getArticleId).setHeader("Artikel-Nummer").setSortable(true);
		grid.addColumn(ArticleStock::getAmount).setHeader("Menge");
		grid.addColumn(ArticleStock::getMhd).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
		grid.addColumn(ArticleStock::getPackaging).setHeader("Verpackung");
		
		HorizontalLayout horiFirstLayout = new HorizontalLayout();
		DatePicker picker = new DatePicker("MDH");
		availableItems	= new Select<Item>();
		availableItems.setLabel("Artikel");
		IntegerField amount = new IntegerField("Menge");
		
		
		HorizontalLayout horiSecondLayout = new HorizontalLayout();
		Button addBtn = new Button("einlagern");
		addBtn.addClickListener(e -> {
			log.info("Artikel wird eingelagert: "+availableItems.getValue()+" mit Menge: "+amount.getValue());
			
			artService.addToStock(availableItems.getValue(), amount.getValue(), picker.getValue());
			
			UI.getCurrent().getPage().reload();
		});
		horiSecondLayout.add(addBtn);
		
		horiFirstLayout.add(availableItems, amount, picker);
		
		
		
		add(routeTabs,grid,horiFirstLayout,horiSecondLayout);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			//redirect
			event.rerouteTo(LoginUi.class);
		}
		
		availableItems.setItems(artService.getAllItems());

		grid.setItems(artService.getAllItemsInStock());
	}
}