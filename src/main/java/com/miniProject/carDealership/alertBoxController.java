package com.miniProject.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class alertBoxController {
    @FXML
    private javafx.scene.control.Button tryAgain;
    @FXML
    private javafx.scene.text.Text text = new Text();
    static String a;
    static String b;
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 360,120);
        Stage stage = new Stage();
        stage.setTitle(b);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onTryAgainButtonClicked() {
        Stage stage = (Stage) tryAgain.getScene().getWindow();
        stage.hide();
    }
    public void success() throws IOException{
        alertBoxController.a = "Success!";
        alertBoxController.b = "Congratulations!";
        launch();
    }
    public void generalError(String x) throws IOException {
        alertBoxController.a = x;
        alertBoxController.b = "Error!";
        launch();
    }
    public void setText() {
        if(a=="Can't connect to internet"){
            tryAgain.setVisible(false);
        }
        text.setText(a);
        System.out.println(a);
    }
    public void initialize(){
        setText();
    }
}
