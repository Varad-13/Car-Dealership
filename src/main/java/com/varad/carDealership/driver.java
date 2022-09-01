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
        if(database.connectDatabase()){
            FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 320);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        else{
            alertBoxController alert = new alertBoxController();
            alert.generalError();
        }
    }

    public static void main(String[] args) throws IOException {
        Platform.setImplicitExit(false);
        //db database = new db();
        //if(database.connectDatabase()){
            launch();
        //}
        //else{
            //alertBoxController alert = new alertBoxController();
            //alert.generalError();
        //}
    }
}