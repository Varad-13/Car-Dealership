package com.miniProject.carDealership;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private TextField tnumber;
    @FXML
    private RadioButton radioUser;
    @FXML
    private RadioButton radioSeller;
    @FXML
    private DatePicker tdob;
    @FXML

    private TextField tpincode;
    @FXML
    protected void onConfirmButtonClick() throws IOException, SQLException {
        try {
            loginController login = new loginController();
            registerController register = new registerController();
            dbUser jdbc = new dbUser();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            String address = taddress.getText();
            String number = tnumber.getText();
            int pincode = Integer.parseInt(tpincode.getText());
            int type;
            if(radioUser.isSelected()) {
                type = 1;
            } else{
                if (radioSeller.isSelected()) {
                    type = 2;
                }
                else {
                    type = 3;
                }
            }
            LocalDate dob = tdob.getValue();
            jdbc.insertLoginDetails(fullName, emailId, password);
            jdbc.insertUserData(fullName, emailId, address, number, type, Date.valueOf(dob), pincode);
            register.close();
            login.launch();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onLoginButtonClicked() throws IOException {
        loginController login = new loginController();
        close();
        login.launch();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 580);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
    public void close() throws IOException{
        Stage stage = (Stage) Confirm.getScene().getWindow();
        stage.hide();
    }
}