package com.grishel.languageschool.client.fxml;


import java.io.IOException;

import com.grishel.languageschool.client.JavaFXApp;
import com.grishel.languageschool.client.utils.ClientConnection;
import com.grishel.languageschool.shared.connection.Request;
import com.grishel.languageschool.shared.connection.Request.RequestType;
import com.grishel.languageschool.shared.connection.Response;
import com.grishel.languageschool.shared.pojo.User;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	
	
	@FXML
	private void login() {
		String login = loginField.getText();
		String password = passwordField.getText();
		System.out.println(login);
		System.out.println(password);
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		ClientConnection.getConnection().send(new Request<User>(RequestType.LOGIN, user).toString());
		Response<User> response;
		try {
			response = Response.fromString(ClientConnection.getConnection().recieve(), User.class);
			System.out.println(response.toString());
			if(response.getData() == null) {
				System.out.println("Неправильный пароль");
			}
			else {
				ClientConnection.getConnection().setUser(response.getData());
				JavaFXApp.setRoot("lessons");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void register() {
		String login = loginField.getText();
		String password = passwordField.getText();
		if (login.equals("") || password.equals("")) {return;}
		System.out.println(login);
		System.out.println(password);
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		ClientConnection.getConnection().send(new Request<User>(RequestType.REGISTER, user).toString());
		Response<User> response;
		try {
			response = Response.fromString(ClientConnection.getConnection().recieve(), User.class);
			if(response.getData() == null) {
				System.out.println("Неправильный пароль");
			}
			else {
				ClientConnection.getConnection().setUser(response.getData());
				JavaFXApp.setRoot("lessons");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
