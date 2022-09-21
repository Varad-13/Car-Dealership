package com.miniProject.carDealership;
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
        System.out.println("Login Button Clicked!");
        try {
            alertBoxController alert = new alertBoxController();
            db jdbc = new db();
            String emailId = Email.getText();
            String passwd = Password.getText();
            if(jdbc.checkCredentials(emailId, passwd))
            {
                alert.success();
            }
            else{
                alert.generalError("Check Credentials!");
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
            close(stage);
            registerController register = new registerController();
            register.launch(stage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void launch(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 320);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void close(Stage stage) throws IOException {
        stage.hide();
    }
}
