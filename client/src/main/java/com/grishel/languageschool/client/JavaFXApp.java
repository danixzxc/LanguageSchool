package com.grishel.languageschool.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

import com.grishel.languageschool.client.utils.ClientConnection;

/**
 * JavaFX App
 */
public class JavaFXApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	ClientConnection.createConnection(new Socket("127.0.0.1", 16969));
        scene = new Scene(loadFXML("login"), 640, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXApp.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void stop() {
    	try {
			ClientConnection.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}