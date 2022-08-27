package com.varad.carDealership;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.stage.*;
import java.io.IOException;

public class LoginController {
    @FXML
    private javafx.scene.control.Button Register;
    @FXML
    protected void onRegisterButtonClick() {
        try {
            Stage stage = (Stage) Register.getScene().getWindow();
            stage.hide();
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("Register.fxml"));
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
