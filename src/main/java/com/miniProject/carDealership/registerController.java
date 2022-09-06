package com.miniProject.carDealership;
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
    protected void onConfirmButtonClick() throws IOException {
        try {
            loginController login = new loginController();
            registerController register = new registerController();
            db jdbc = new db();
            Stage stage = (Stage) Confirm.getScene().getWindow();
            //Stage stage = new Stage();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            String address = taddress.getText();
            jdbc.insertLoginDetails(fullName, emailId, password);
            jdbc.insertUserData(fullName, emailId, address);
            register.close(stage);
            login.launch(stage);
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void launch(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 580);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
    public void close(Stage stage) throws IOException{
        stage.hide();
    }
}