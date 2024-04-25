package com.iu.JaWa.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/login")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public MainView() {
		TextField userName = new TextField();
		PasswordField password = new PasswordField();
		add(userName,
		password,
		new Button("Login", e -> Notification.show(userName.getValue()+" "+password.getValue())));
	}
}