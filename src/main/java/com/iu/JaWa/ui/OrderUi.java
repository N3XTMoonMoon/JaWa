package com.iu.JaWa.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import component.RouteTabs;

@Route("/order")
public class OrderUi extends VerticalLayout{

	private static final long serialVersionUID = 8543455234987098118L;

	public OrderUi() {
		
		RouteTabs routeTabs =  RouteTabs.createTabs();
		
		TextField field = new TextField("sdfsdf");
		
		add(routeTabs,field);
	}
}
