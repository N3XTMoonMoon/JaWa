package com.iu.JaWa.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.iu.JaWa.service.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/login")
@PageTitle("Login | JaWa")
public class LoginUi extends VerticalLayout {

	private static final long serialVersionUID = -1614052867780794376L;

	@Autowired
	private LoginService loginService;

	public LoginUi() {
		
		setAlignItems(Alignment.CENTER);
		
		
		
		TextField userName = new TextField();
		PasswordField password = new PasswordField();
//		Notification.show(userName.getValue()+" "+password.getValue();
		Button loginBtn = new Button("Login");
		loginBtn.addClickListener(e -> {

			String response = loginService.login(userName.getValue(), password.getValue().hashCode());

			if (response.equals("SUCCESS")) {
				
				loginService.saveCookie();
				Notification.show("Erfolgreich eingeloggt");
				
				// redirect
				loginBtn.getUI().ifPresent(ui -> ui.navigate("/sell"));
			} else {
				Notification.show("Falsches Passwort");
			}
		});

		add(userName, password, loginBtn);
	}
	
	//TODO: move to service
	
}