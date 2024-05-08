package com.iu.JaWa.ui;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.iu.JaWa.entity.Categorie;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@PageTitle("Artikelstammdaten")
@Route("/article")
public class ArticleUi extends VerticalLayout implements BeforeEnterObserver{
	private static final long serialVersionUID = 3847590147025409919L;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ArticleService artService;
	
	Grid<Item> grid;
	Select<Categorie> availabelCategories;
	Select<Categorie> availabelCategorieSelect;
	
	TextField articleName, articleDescrition, articleBrand;
	NumberField price;
	
	Item selectedGridItem;
	
	/**
	 * TODO:
	 * - Filtern nach Kategorie y
	 * - Auf-/abstiegend sortieren in allen Spalten y
	 * - Artikelanlage in drop down (eigener Bereich)
	 * 
	 * - Anlegen von Artikeln direkt hinzufügen
	 * - Lagerbestand anpassen können
	 * 
	 * - item Detail
	 * 	- Name, Preis anzeigen
	 * 	- beim Aufklappen noch den Rest wie art NR, Kategorie und so anzeigen y
	 */
	public ArticleUi(){
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		
		grid = new Grid<>(Item.class,false);
		grid.addColumn(Item::getName).setHeader("Name").setWidth("120px");
		grid.addColumn(Item::getDescription).setHeader("Beschreibung");
		grid.addColumn(Item::getPrice).setHeader("Preis").setSortable(true);
		
		grid.addItemClickListener(e -> {
			
			selectedGridItem = e.getItem();

			articleName.setValue(selectedGridItem.getName());
			articleDescrition.setValue(selectedGridItem.getDescription());
			price.setValue(selectedGridItem.getPrice());
			availabelCategories.setValue(selectedGridItem.getCategory());
			articleBrand.setValue(selectedGridItem.getBrand());
			
			System.out.println(e.getItem().toString());
		});
		
		//---------------------------------------
		//Add new article stuff
		
		Button newArticleBtn = new Button("Clear");
		//reset all value fields
		newArticleBtn.addClickListener(e -> {
			articleName.setValue("");
			articleDescrition.setValue("");
			price.setValue(0.0);
			articleBrand.setValue("");
			availabelCategories.setValue(null);
		});
		
		articleName = new TextField("ArtikelName");
		articleDescrition = new TextField("Artikelbeschreibung");
		price = new NumberField("Preis");
		articleBrand = new TextField("Marke");

		availabelCategories = new Select<Categorie>();
		availabelCategories.setLabel("Kategorien");
		availabelCategories.setItemLabelGenerator(Categorie::getName);
		
		
		
		Button btn = new Button("Speichern",(e -> {
			
			Item item = null;
			
			Optional<Categorie> selectedCategorie = availabelCategories.getOptionalValue();
			
			if(selectedCategorie.isEmpty()){
				
				ConfirmDialog warning = new ConfirmDialog();
				warning.setHeader("Keine Kategorie ausgewählt");
				warning.setConfirmText("OK");
				
				//THROW ERROR = show missing categorie
			}else {
				
			
			/**
			 * check if item is selected from grid.
			 * If so save item in "cache" as variable.
			 * 
			 * If no item is selected create new one
			 */
			
			if(selectedGridItem==null) {
				
				
					//get selected category
				item = new Item(selectedCategorie.get(),
					articleName.getValue(),
					articleDescrition.getValue(),
					price.getValue(),
					0,
					articleBrand.getValue());
				
			}else {
				item=new Item(selectedGridItem.getArticleNumber(),availabelCategories.getOptionalValue().get() ,
						articleName.getValue(),
						articleDescrition.getValue(),
						price.getValue(),
						selectedGridItem.getStock(),articleBrand.getValue());//maybe change getStock to fieldValue
			}}
			
			try {
				
				artService.saveArticleToDb(item);
				Notification.show("Speichern des Artikels: "+articleName.getValue()+" erfolgreich");
			}catch(InvalidDataAccessApiUsageException err) {
				
				Notification.show("Fehlendes Argument. Haben Sie alle Felder ausgefüllt?");
				System.out.println(err);
				err.printStackTrace();
			}catch(Exception err) {
				
				Notification.show("Fehler");
				System.out.println(err);
				err.printStackTrace();
			}
			
			
			//TODO: Add refresh of page/grid
			
		}));
		btn.setAutofocus(true);
		
		FormLayout inputLayout = new FormLayout();
		inputLayout.add(articleName, articleDescrition, price, articleBrand, availabelCategories);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(newArticleBtn,btn);
		
		inputLayout.add(buttonLayout);
		inputLayout.setResponsiveSteps(
		        // Use one column by default
		        new ResponsiveStep("0", 1),
		        // Use two columns, if layout's width exceeds 500px
		        new ResponsiveStep("500px", 2));
		
		Accordion accordion = new Accordion();
		accordion.add("Artikeldetails",inputLayout);
		
		TextField category = new TextField("Kategoriename");
		FormLayout categoryInputLayout = new FormLayout();
		Button categoryBtn = new Button("Hinzufügen");
		categoryBtn.addClickListener(e -> {
			try {
				artService.saveCategory(category.getValue());
				Notification.show("Kategorie: "+category.getValue()+" hinzugefügt");
				UI.getCurrent().getPage().reload();
			}catch(Exception ex) {
				Notification.show(ex.getMessage());
				ex.printStackTrace();
			}
			
		});
		categoryInputLayout.add(category);
		categoryInputLayout.add(categoryBtn);
		
		availabelCategorieSelect = new Select<Categorie>();
		availabelCategorieSelect.setLabel("Kategorien");
		availabelCategorieSelect.setItemLabelGenerator(Categorie::getName);
		
		Button categoryRemoveBtn = new Button("Löschen");
		categoryRemoveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
		        ButtonVariant.LUMO_ERROR);
		categoryRemoveBtn.addClickListener(e -> {
			try {
				artService.deleteCategory(availabelCategorieSelect.getValue());
				Notification.show("Kategorie: "+category.getValue()+" gelöscht");
				UI.getCurrent().getPage().reload();
			}catch(Exception ex) {
				Notification.show(ex.getMessage());
				ex.printStackTrace();
			}
		});
		
		categoryInputLayout.add(availabelCategorieSelect,categoryRemoveBtn);
		accordion.add("Kategoriedetails",categoryInputLayout);
		
		
		
		
		add(routeTabs,grid,accordion);
	}
	

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			//redirect
			event.rerouteTo(LoginUi.class);
		}
		
		availabelCategories.setItems(artService.getAllCategories());
		availabelCategorieSelect.setItems(artService.getAllCategories());
		

		grid.setItems(artService.getAllItems());
	}
}
