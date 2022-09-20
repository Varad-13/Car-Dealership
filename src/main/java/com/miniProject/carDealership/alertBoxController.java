package com.miniProject.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class alertBoxController {
    @FXML
    private javafx.scene.control.Button tryAgain;
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
    @FXML
    protected void onTryAgainButtonClicked() {
        Stage stage = (Stage) tryAgain.getScene().getWindow();
        stage.hide();
    }
    public void generalError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360,120);
        Stage stage = new Stage();
        stage.setTitle("Failed!");
        stage.setScene(scene);
        stage.show();
    }
    public void success() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360,120);
        Stage stage = new Stage();
        stage.setTitle("Congratulations!");
        stage.setScene(scene);
        stage.show();
    }
}
