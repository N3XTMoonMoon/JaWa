package com.iu.JaWa.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/user")
public class UserAdministrationUI extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 9029462528351170430L;
	
	@Autowired
	private LoginService loginService;

	public UserAdministrationUI() {
		
		RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel", ArticleUi.class));
		
		TextField userName = new TextField();
		
		add(routeTabs,userName);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			//redirect
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			
			event.rerouteTo(LoginUi.class);
		}
	}
}
