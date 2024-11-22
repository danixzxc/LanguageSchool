package com.grishel.languageschool.client.fxml;

import java.io.IOException;

import com.grishel.languageschool.client.JavaFXApp;

import javafx.fxml.FXML;

public class LessonsController {
	@FXML
	private void debugLesson() {
		try {
			JavaFXApp.setRoot("study");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void debugUserInfo() {
		try {
			JavaFXApp.setRoot("userinfo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void debugStats() {
		try {
			JavaFXApp.setRoot("studentgradestats");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}