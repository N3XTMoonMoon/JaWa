package com.iu.JaWa.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.Categorie;
import com.iu.JaWa.entity.Item;
import com.iu.JaWa.service.ArticleService;
import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/article")
public class ArticleUi extends VerticalLayout implements BeforeEnterObserver{
	private static final long serialVersionUID = 3847590147025409919L;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ArticleService artService;
	
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	public ArticleUi() {
		
		RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel", ArticleUi.class));
		
		TextField articleName = new TextField("ArtikelName");
		TextField articleDescrition = new TextField("Artikelbeschreibung");
		NumberField price = new NumberField("Preis");

		ComboBox<Categorie> availabelCategories = new ComboBox<Categorie>("Kategorie",categories);
		
		
		Button btn = new Button("Hinzufügen",(e -> {
			
			Item item = new Item(new Categorie(1,"Lebensmittel") ,
					articleName.getValue(),
					articleDescrition.getValue(),
					price.getValue(),
					0);
			
			artService.saveArticleToDb(item);
			
			Notification.show("Hinzufügen des Artikels: "+articleName.getValue());
		}));
		
		add(routeTabs,articleName,articleDescrition,price,availabelCategories,btn);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			//redirect
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			
			event.rerouteTo(LoginUi.class);
		}
		
		//TODO: Fill availabelCategories
		categories.add(new Categorie(1,"Lebensmittel"));
		categories.add(new Categorie(2,"Haushalt"));
	}
}
