package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class userDetailsController {
    @FXML
    private TextArea taddress = new TextArea();
    @FXML
    private TextField tnumber = new TextField();
    @FXML
    private RadioButton radioUser = new RadioButton();
    @FXML
    private RadioButton radioSeller = new RadioButton();
    @FXML
    private TextField tpincode = new TextField();
    @FXML
    private javafx.scene.control.Button goBack;
    dbUser jdbc = new dbUser();
    public void onChangeEmailClicked() throws IOException {
        alertBoxController alert = new alertBoxController();
        alert.generalError("Under Development");
    }
    public void onBackButtonClicked() throws IOException {
        close();
        loginController login = new loginController();
        login.openHomepage();
    }
    public void onConfirmButtonClicked() throws SQLException, IOException {
        System.out.println("Confirm clicked");
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
        jdbc.updateUserDetails(address, number, type, pincode);
        setInfo();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("editUserDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 685, 415);
        Stage stage = new Stage();
        stage.setTitle("Edit User Details");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() throws SQLException, IOException {
        setInfo();
    }

    private void setInfo() throws SQLException, IOException {
        jdbc.getUserDetails();
        taddress.setText(jdbc.address);
        tnumber.setText(jdbc.number);
        tpincode.setText(String.valueOf(jdbc.pincode));
        System.out.println(jdbc.checkUsertype());
        if(jdbc.checkUsertype()==1) {
            radioUser.setSelected(true);
        } else {
            radioSeller.setSelected(true);
        }
    }

    public void close() {
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.hide();
    }
}
