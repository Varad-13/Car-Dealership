package com.miniProject.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class alertBoxController {
    @FXML
    private javafx.scene.control.Button tryAgain;
    @FXML
    private javafx.scene.text.Text text = new Text();
    @FXML
    private TextField textField = new TextField();
    @FXML
    private Label test = new Label();
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
    @FXML
    protected void onTryAgainButtonClicked() {
        Stage stage = (Stage) tryAgain.getScene().getWindow();
        stage.hide();
    }
    public void success() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360,120);
        Stage stage = new Stage();
        stage.setTitle("Congratulations!");
        stage.setScene(scene);
        stage.show();
    }
    public void generalError() throws IOException {
        launch();
    }
    public void setText() {
        test.setText("Error!");
        textField.setText("Error!");
        text.setText("Error!");
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360,120);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){
        setText();
    }
}
