package com.grishel.languageschool.client;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
    	JavaFXApp.setRoot("primary");
    }
}