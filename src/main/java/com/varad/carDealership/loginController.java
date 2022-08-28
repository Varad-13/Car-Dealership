package com.varad.carDealership;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.scene.control.Label;
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
    private javafx.scene.control.Button Login;
    @FXML
    protected void onLoginButtonClick() {
        try {
            com.varad.carDealership.db jdbc = new db();
            String emailId = Email.getText();
            String passwd = Password.getText();
            boolean flag = jdbc.checkCredentials(emailId, passwd);
            if(flag)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("alertBox.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 200,120);
                Stage window = new Stage();
                window.setTitle("Congratulations!");
                window.setScene(scene);
                window.show();
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
            FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 680, 580);
            Stage window = new Stage();
            window.setTitle("Register");
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
