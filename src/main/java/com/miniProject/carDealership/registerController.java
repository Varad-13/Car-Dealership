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
    protected void onConfirmButtonClick() {
        try {
            loginController login = new loginController();
            registerController register = new registerController();
            db jdbc = new db();
            Stage stage = (Stage) Confirm.getScene().getWindow();
            String fullName = tname.getText();
            String emailId = temail.getText();
            String password = passwd.getText();
            String address = taddress.getText();
            String number = tnumber.getText();
            int pincode = Integer.parseInt(tpincode.getText());
            String type = null;
            if(radioUser.isSelected()) {
                type = "Buyer";
            } else if (radioSeller.isSelected()) {
                type = "Seller";
            }
            else {
                alertBoxController alert = new alertBoxController();
                alert.generalError();
            }
            LocalDate dob = tdob.getValue();

            jdbc.insertLoginDetails(fullName, emailId, password);
            jdbc.insertUserData(fullName, emailId, address, number, type, Date.valueOf(dob), pincode);
            register.close(stage);
            login.launch(stage);
        } catch (IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    protected void onLoginButtonClicked() throws IOException {
        loginController login = new loginController();
        registerController register = new registerController();
        Stage stage = (Stage) Confirm.getScene().getWindow();
        register.close(stage);
        login.launch(stage);
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