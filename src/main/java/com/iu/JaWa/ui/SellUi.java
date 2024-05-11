package com.iu.JaWa.ui;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.CurrentStock;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@Route("/sell")
@PageTitle("Einkauf")
public class SellUi extends VerticalLayout implements BeforeEnterObserver{
private static final long serialVersionUID = 2045973054759982104L;
	Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ArticleService artService;

	Grid<CurrentStock> grid;
	
	CurrentStock selectedEntry;

	public SellUi() {
		
RouteTabs routeTabs =  RouteTabs.createTabs();
		
		grid = new Grid<CurrentStock>();
		grid.addColumn(CurrentStock::getName).setHeader("Artikel-Nummer").setSortable(true);
		grid.addColumn(CurrentStock::getAmount).setHeader("Menge");
//		grid.addColumn(CurrentStock::get).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
		grid.addColumn(CurrentStock::getMhd).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
		
		grid.addSelectionListener(selection -> {
		    Optional<CurrentStock> SelectedEntry = selection.getFirstSelectedItem();
		    if (SelectedEntry.isPresent()) {
		    	selectedEntry = SelectedEntry.get();
		    }
		});
				
		Button logoutBtn = new Button("Logout");
		logoutBtn.addClickListener(e -> {
			loginService.logout();
			logoutBtn.getUI().ifPresent(ui -> ui.navigate("/login"));}
		);

		add(routeTabs,routeTabs,grid,logoutBtn);
	}
	
	private void reload() {
		UI.getCurrent().getPage().reload();
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			//redirect
			event.rerouteTo(LoginUi.class);
		}
		
		grid.setItems(artService.getAllCurrentItemsInStock());
	}
}
