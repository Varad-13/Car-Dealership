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
            sellerController seller = new sellerController();
            db jdbc = new db();
            String emailId = Email.getText();
            String passwd = Password.getText();
            Stage stage = new Stage();
            if(jdbc.checkCredentials(emailId, passwd))
            {
                switch(jdbc.checkUsertype()) {
                    case 0:
                        alert.generalError("Usertype not found please contact support");
                        break;
                    case 1:
                        alert.success("Buyer");
                        break;
                    case 2:
                        seller.launch(stage);
                        break;
                    case 3:
                        alert.success("Admin");
                        break;
                }
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
