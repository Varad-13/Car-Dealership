package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sellerController {
    @FXML
    private javafx.scene.control.Button exit;
    @FXML
    public void onUserButtonClicked() throws IOException {
       close();
       userDetailsController user = new userDetailsController();
       user.launch();
    }
    @FXML
    public void onExitButtonClicked() throws IOException {
        alertBoxController alert = new alertBoxController();
        alert.exit("Are you sure you want to exit?");
    }
    @FXML
    public void onAddListingClicked() throws IOException {
        close();
        carListController car = new carListController();
        car.launch();
    }
    public void launch() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("sellerHomepage.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 500, 480);
    Stage stage = new Stage();
    stage.setTitle("Driftstore");
    stage.setScene(scene);
    stage.show();
    }
    public void close(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.hide();
    }
}
