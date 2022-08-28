package com.varad.carDealership;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class registerController {
    @FXML
    private javafx.scene.control.Button Confirm;
    @FXML
    private TextField tname;
    @FXML
    private PasswordField passwd;
    @FXML
    private TextField temail;
    @FXML
    private TextArea taddress;
    @FXML
    protected void onConfirmButtonClick() {
        try {
            Stage stage = (Stage) Confirm.getScene().getWindow();
            stage.hide();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            String address = taddress.getText();
            com.varad.carDealership.db jdbc = new db();
            jdbc.insertLoginDetails(fullName, emailId, password);
            jdbc.insertUserData(fullName, emailId, address);
            FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 320);
            Stage window = new Stage();
            window.setTitle("Login");
            window.setScene(scene);
            window.show();
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}