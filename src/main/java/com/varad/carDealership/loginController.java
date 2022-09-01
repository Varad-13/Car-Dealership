package com.varad.carDealership;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.*;
import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private javafx.scene.control.Button Register;
    @FXML
    protected void onLoginButtonClick() {
        try {
            com.varad.carDealership.db jdbc = new db();
            String emailId = Email.getText();
            String passwd = Password.getText();
            if(jdbc.checkCredentials(emailId, passwd))
            {
                alertBoxController alert = new alertBoxController();
                alert.success();
            }
            else{
                alertBoxController alert = new alertBoxController();
                alert.generalError();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onRegisterButtonClick() {
        try {
            Stage stage = (Stage) Register.getScene().getWindow();
            stage.hide();
            registerController register = new registerController();
            register.launch(stage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
