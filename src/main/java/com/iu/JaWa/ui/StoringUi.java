package com.iu.JaWa.ui;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.Category;
import com.iu.JaWa.entity.CurrentStock;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

	Grid<CurrentStock> grid;
	Select<Item> availableItems;
	
	CurrentStock selectedEntry;
	
	public StoringUi() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		grid = new Grid<CurrentStock>();
		grid.addColumn(CurrentStock::getName).setHeader("Artikel-Nummer").setSortable(true);
		grid.addColumn(CurrentStock::getAmount).setHeader("Menge");
		grid.addColumn(CurrentStock::getMhd).setHeader("Mindesthaltbarkeitsdatum").setSortable(true);
		
		grid.addSelectionListener(selection -> {
		    Optional<CurrentStock> SelectedEntry = selection.getFirstSelectedItem();
		    if (SelectedEntry.isPresent()) {
		    	selectedEntry = SelectedEntry.get();
		    }
		});
		
		Button removeStockBtn = new Button("Eintrag löschen");
		removeStockBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
		removeStockBtn.addClickListener(e -> {
			if(selectedEntry!=null) {
				artService.removeStockEntry(selectedEntry);
			}
			reload();
		});
		
		
		HorizontalLayout horiFirstLayout = new HorizontalLayout();
		DatePicker picker = new DatePicker("MDH");
		availableItems	= new Select<Item>();
		availableItems.setLabel("Artikel");
		IntegerField amount = new IntegerField("Menge");
		
		
		HorizontalLayout horiSecondLayout = new HorizontalLayout();
		Button addBtn = new Button("einlagern");
		addBtn.addClickListener(e -> {
			log.info("Artikel wird eingelagert: "+availableItems.getValue()+" mit Menge: "+amount.getValue());
			
			if(availableItems.getValue().getCategory().equals(new Category("Lebensmittel"))) {
				
				if(availableItems.getValue()!=null && picker.getValue()!= null) {
					artService.addToStock(availableItems.getValue(), amount.getValue(), picker.getValue());
				}
			}else {
				artService.addToStock(availableItems.getValue(), amount.getValue(), LocalDate.of(9999, 12, 31));
			}
			
			
			reload();
		});
		horiSecondLayout.add(addBtn);
		
		horiFirstLayout.add(availableItems, amount, picker);
		
		
		
		add(routeTabs,grid,removeStockBtn,horiFirstLayout,horiSecondLayout);
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
		
		availableItems.setItems(artService.getAllItems());

		grid.setItems(artService.getAllCurrentItemsInStock());
	}
}