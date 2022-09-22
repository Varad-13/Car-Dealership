package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class carListController {
    @FXML
    private javafx.scene.control.Button goBack;
    @FXML
    public void onGoBackClicked() throws IOException {
        loginController login = new loginController();
        close();
        login.openHomepage();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("carList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 570, 585);
        Stage stage = new Stage();
        stage.setTitle("Driftstore");
        stage.setScene(scene);
        stage.show();
    }
    public void close(){
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.hide();
    }
}
