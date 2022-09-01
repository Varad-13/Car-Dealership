package com.varad.carDealership;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        db database = new db();
        if(database.checkConnection()){
            FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 320);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args){
        Platform.setImplicitExit(false);
        launch();
    }
}