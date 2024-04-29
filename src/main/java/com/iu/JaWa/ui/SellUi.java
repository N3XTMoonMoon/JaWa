package com.iu.JaWa.ui;


import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/sell")
public class SellUi extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 7570833660999463326L;

	@Autowired
	private LoginService loginService;

	public SellUi() {
		DatePicker picker = new DatePicker();
		
		Tab einkaufTab = new Tab("Einkauf");
		Tab userAddingTab = new Tab("Nutzer");
		Tab itemAddingTab = new Tab("Artikel");
		Tabs tabs = new Tabs(einkaufTab,userAddingTab,itemAddingTab);
		tabs.addSelectedChangeListener(e -> {
			Notification.show("Gewechselt zu: "+e.getSelectedTab().getLabel());
			
		});
		
		RouteTabs routeTabs = new RouteTabs();
		routeTabs.add(new RouterLink("Einkauf", SellUi.class));
		routeTabs.add(new RouterLink("Nutzer", UserAdministrationUI.class));
		routeTabs.add(new RouterLink("Artikel", ArticleUi.class));
		
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
