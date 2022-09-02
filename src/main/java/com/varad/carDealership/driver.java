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
            loginController login = new loginController();
            login.launch(stage);
        }
    }

    public static void main(String[] args){
        Platform.setImplicitExit(false);
        launch();
    }
}