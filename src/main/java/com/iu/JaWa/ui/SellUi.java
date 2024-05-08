package com.iu.JaWa.ui;


import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@Route("/sell")
public class SellUi extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 7570833660999463326L;

	@Autowired
	private LoginService loginService;

	public SellUi() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		DatePicker picker = new DatePicker("MDH");
		
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addClickListener(e -> {
			loginService.logout();
			logoutBtn.getUI().ifPresent(ui -> ui.navigate("/login"));}
		);

		add(routeTabs,picker,logoutBtn);
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
