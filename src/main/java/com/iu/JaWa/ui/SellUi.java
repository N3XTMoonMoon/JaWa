package com.iu.JaWa.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.CurrentStock;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.provider.ListDataProvider;
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

	Grid<CurrentStock> availableArticleGrid;
	List<CurrentStock> availableArticleList;
	ListDataProvider<CurrentStock> availableStockDataProvider;
	
	
	Grid<CurrentStock> cartArticles;
	List<CurrentStock> cartArticleList;
	ListDataProvider<CurrentStock> cartArticleDataProvider;
	
	CurrentStock selectedEntry;
	
	IntegerField articleAmount;

	public SellUi() {
		
RouteTabs routeTabs =  RouteTabs.createTabs();

		availableArticleGrid = new Grid<CurrentStock>();
		
		availableArticleGrid.addColumn(CurrentStock::getName).setHeader("Artikel-Nummer").setSortable(true);
		availableArticleGrid.addColumn(CurrentStock::getAmount).setHeader("Menge");
		availableArticleGrid.addColumn(CurrentStock::getCategory).setHeader("Kategorie").setSortable(true);
		availableArticleGrid.addColumn(CurrentStock::getMhd).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
				
		//TODO: add multi selection
		availableArticleGrid.addSelectionListener(selection -> {
		    Optional<CurrentStock> SelectedEntry = selection.getFirstSelectedItem();
		    if (SelectedEntry.isPresent()) {
		    	articleAmount.setValue(SelectedEntry.get().getAmount());
		    	selectedEntry = SelectedEntry.get();
		    }
		});
		
		
		
		cartArticles = new Grid<CurrentStock>();
		cartArticles.addColumn(CurrentStock::getName).setHeader("Artikel-Nummer").setSortable(true);
		cartArticles.addColumn(CurrentStock::getAmount).setHeader("Menge");
		cartArticles.addColumn(CurrentStock::getCategory).setHeader("Kategorie").setSortable(true);
		cartArticles.addColumn(CurrentStock::getMhd).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
		
		cartArticles.addSelectionListener(selection -> {
		    Optional<CurrentStock> SelectedEntry = selection.getFirstSelectedItem();
		    if (SelectedEntry.isPresent()) {
		    	selectedEntry = SelectedEntry.get();
		    }
		});
		
		articleAmount = new IntegerField("Anzahl der Artikel");
		articleAmount.setStepButtonsVisible(true);
		articleAmount.setMin(0);
		
		//TODO: Amount hinzufügen und die Items mit amount hinzufügen
		Button addBtn = new Button("Zum Warenkorb hinzufügen");
		addBtn.addClickListener(click -> {
			if(selectedEntry == null) {
				Notification.show("Bitte wählen Sie einen Artikel aus");
			} else {
				addStockItemToCart(selectedEntry);
				selectedEntry = null;//reset, so the same item can´t be aded multiple times!
			}
		});
		
		Button removeBtn = new Button("Ausgewählten Artikel entfernen");
		removeBtn.addClickListener(click -> {
			removeStockItemFromCart(selectedEntry);
		});
		
		Button orderBtn = new Button("Bestellen");
		orderBtn.addClickListener(click -> {
			//TODO: Bestll Tabelle mit Status hinzufügen abei noch amount beachten
			//TODO: add ConfirmDialog
		});
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addClickListener(e -> {
			loginService.logout();
			logoutBtn.getUI().ifPresent(ui -> ui.navigate("/login"));}
		);
		
		//pack pageLayout
		VerticalLayout vert = new VerticalLayout();
		
		H2 first = new H2("Verfügbare Artikel");
		H2 second = new H2("Warenkorb");
		vert.add(first);
		vert.add(availableArticleGrid);
		
			HorizontalLayout hor = new HorizontalLayout();
			hor.setAlignItems(FlexComponent.Alignment.BASELINE);
			hor.add(articleAmount);
			hor.add(addBtn);
		vert.add(hor);
		
		vert.add(second);
		vert.add(cartArticles);
		
		vert.add(new HorizontalLayout(removeBtn,orderBtn));
		
		vert.add(logoutBtn);

		add(routeTabs,vert);
	}
	
	private void addStockItemToCart(CurrentStock selected) {
		availableArticleList.remove(selected);
		cartArticleList.add(selected);
		cartArticleDataProvider.refreshAll();
		availableStockDataProvider.refreshAll();
	}
	
	private void removeStockItemFromCart(CurrentStock selected) {
		availableArticleList.add(selected);
		cartArticleList.remove(selected);
		cartArticleDataProvider.refreshAll();
		availableStockDataProvider.refreshAll();
	}
	
	//uncomment when needed
//	private void reload() {
//		UI.getCurrent().getPage().reload();
//	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			//redirect
			event.rerouteTo(LoginUi.class);
		}
		availableArticleList = new ArrayList<>(artService.getAllCurrentItemsInStock());
		availableStockDataProvider = new ListDataProvider<CurrentStock>(availableArticleList);
		availableArticleGrid.setDataProvider(availableStockDataProvider);
//		availableArticles.setItems();
		cartArticleList = new ArrayList<CurrentStock>();
		cartArticleDataProvider = new ListDataProvider<CurrentStock>(cartArticleList);
		cartArticles.setDataProvider(cartArticleDataProvider);
	}
}
