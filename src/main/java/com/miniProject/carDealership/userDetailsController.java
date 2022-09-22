package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class userDetailsController {
    @FXML
    private javafx.scene.control.Button goBack;
    public void onBackButtonClicked() throws IOException {
        close();
        loginController login = new loginController();
        login.openHomepage();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("editUserDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 685, 560);
        Stage stage = new Stage();
        stage.setTitle("Edit User Details");
        stage.setScene(scene);
        stage.show();
    }
    public void close() {
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.hide();
    }
}
