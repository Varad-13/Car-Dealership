package com.varad.carDealership;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

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
    protected void onConfirmButtonClick() {
        try {
            Stage stage = (Stage) Confirm.getScene().getWindow();
            stage.hide();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            com.varad.carDealership.db jdbc = new db();
            jdbc.insertRecord(fullName, emailId, password);
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