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
        System.out.println("Login Clicked!");
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
            registerController register = new registerController();
            register.launch(stage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
