package com.miniProject.carDealership;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.scene.control.Button;
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
    private javafx.scene.control.Button Register = new Button();
    alertBoxController alert = new alertBoxController();
    @FXML
    protected void onLoginButtonClick() {
        System.out.println("Login Button Clicked!");
        try {
            alertBoxController alert = new alertBoxController();
            dbUser jdbc = new dbUser();
            String emailId = Email.getText();
            String passwd = Password.getText();
            if(jdbc.checkCredentials(emailId, passwd))
            {
                close();
                openHomepage();
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
            close();
            registerController register = new registerController();
            register.launch();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void onForgotPasswordClicked() throws IOException {
        alert.generalError("Under Construction");
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 293);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void close() throws IOException {
        Stage stage = (Stage) Register.getScene().getWindow();
        stage.hide();
    }
    public void openHomepage() throws IOException {
        dbUser jdbc = new dbUser();
        sellerController seller = new sellerController();
        userDetailsController userEdit = new userDetailsController();
        switch(jdbc.checkUsertype()) {
            case 0:
                alert.generalError("Usertype not found please contact support");
                break;
            case 1:
                userEdit.launch();
                break;
            case 2:
                seller.launch();
                break;
            case 3:
                alert.success("Admin");
                break;
        }
    }
}
