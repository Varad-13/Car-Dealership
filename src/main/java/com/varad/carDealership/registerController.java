package com.varad.carDealership;
import javafx.fxml.*;
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
            driver login = new driver();
            Stage stage = (Stage) Confirm.getScene().getWindow();
            stage.hide();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            String address = taddress.getText();
            com.varad.carDealership.db jdbc = new db();
            jdbc.insertLoginDetails(fullName, emailId, password);
            jdbc.insertUserData(fullName, emailId, address);
            login.start(stage);
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}