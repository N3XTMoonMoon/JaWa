package com.iu.JaWa.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/user")
public class UserAdministrationUI extends VerticalLayout{

	private static final long serialVersionUID = 9029462528351170430L;

	public UserAdministrationUI() {
		
		RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel", ArticleUi.class));
		
		TextField userName = new TextField();
		
		add(routeTabs,userName);
	}
}
