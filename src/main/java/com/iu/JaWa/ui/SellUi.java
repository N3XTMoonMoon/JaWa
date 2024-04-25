package com.iu.JaWa.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/sell")
public class SellUi extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public SellUi() {
		
		Button logoutBtn = new Button("Logout");
		logoutBtn.addClickListener(e -> 
			logoutBtn.getUI().ifPresent(ui -> ui.navigate("/login"))
		);

		add(logoutBtn);
	}
}
