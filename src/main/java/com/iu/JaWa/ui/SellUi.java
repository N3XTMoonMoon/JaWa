package com.iu.JaWa.ui;


import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("/sell")
public class SellUi extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoginService loginService;

	public SellUi() {
		
		
		
		DatePicker picker = new DatePicker();
		
		MenuBar menu = new MenuBar();
		menu.addItem("Verkauf");
		menu.addItem("Nutzerverwaltung");
		
		SideNav sideNav = new SideNav();
		sideNav.addItem(new SideNavItem("RICHSEDRT"));
		sideNav.setCollapsible(true);
		
		Tab tab = new Tab("Tab");
		Tabs tabs = new Tabs(tab);
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addClickListener(e -> {
			loginService.logout();
			logoutBtn.getUI().ifPresent(ui -> ui.navigate("/login"));}
		);

		add(tabs,picker,logoutBtn);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			//redirect
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			
			event.rerouteTo(LoginUi.class);
//			this.getUI().ifPresent(e -> e.navigate("/login"));
		}
	}
}
