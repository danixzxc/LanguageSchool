package com.grishel.languageschool.client.fxml;

import java.io.IOException;

import com.grishel.languageschool.client.JavaFXApp;

import javafx.fxml.FXML;

public class DebugController {
	@FXML
	private void returnToLessons() {
		System.out.println("назад");
		try {
			JavaFXApp.setRoot("lessons");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
