package com.iu.JaWa.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/article")
public class ArticleUi extends VerticalLayout{
	private static final long serialVersionUID = 3847590147025409919L;
	
	public ArticleUi() {
		
		RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel", ArticleUi.class));
		
		Button btn = new Button("HUHU");
		
		add(routeTabs,btn);
	}
}
