package com.miniProject.carDealership;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        dbUser database = new dbUser();
        if(database.checkConnection()){
            loginController login = new loginController();
            login.launch();
        }
    }

    public static void main(String[] args){
        Platform.setImplicitExit(false);
        launch();
    }
}