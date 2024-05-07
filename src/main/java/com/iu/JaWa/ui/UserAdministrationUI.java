package com.iu.JaWa.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.entity.User;
import com.iu.JaWa.service.LoginService;
import com.iu.JaWa.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@PageTitle("Nutzer-Stammdaten")
@Route("/user")
public class UserAdministrationUI extends VerticalLayout implements BeforeEnterObserver{

	private static final long serialVersionUID = 9029462528351170430L;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService usrService;
	
	List<User> userList = new ArrayList<User>();
	
	Grid<User> grid;

	public UserAdministrationUI() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		grid = new Grid<>(User.class,false);
		grid.addColumn(User::getUserName).setHeader("NutzerName");
		grid.addColumn(User::getRole).setHeader("Rolle");
		
		/**
		 * TODO:
		 * - grid mit item Detail hinzufügen y
		 * 		Nur für admin sichtbar
		 * - pw zurücksetzen können
		 * - neue Nutzer anlegen können
		 * - Rolle Ändern
		 */
		
		add(routeTabs,grid);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		
		if(!loginService.isLoggedIn()) {
			//redirect
			Notification.show("Sie sind nicht angemeldet! Bitte melden Sie sich unter \"/login\" an");
			
			event.rerouteTo(LoginUi.class);
		}
		
		userList.addAll(usrService.getAllUser());
		grid.setItems(userList);
	}
}
