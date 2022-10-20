package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class carFeatureController {
    loginController login = new loginController();
    alertBoxController alert = new alertBoxController();
    @FXML
    private javafx.scene.control.Button goBack;

    @FXML
    private TextField color = new TextField();
    @FXML
    private TextField engine = new TextField();
    @FXML
    private TextField transmission = new TextField();
    @FXML
    private RadioButton acYes = new RadioButton();
    @FXML
    private RadioButton psYes = new RadioButton();
    @FXML
    private RadioButton flYes = new RadioButton();
    @FXML
    private RadioButton alsYes = new RadioButton();
    @FXML
    private RadioButton sunroofYes = new RadioButton();
    @FXML
    private RadioButton convertible = new RadioButton();
    @FXML
    private TextField mods = new TextField();
    @FXML
    private CheckBox engineDam = new CheckBox();
    @FXML
    private CheckBox bodyDam = new CheckBox();
    @FXML
    private CheckBox clutchDam = new CheckBox();
    @FXML
    private CheckBox lampDam = new CheckBox();
    @FXML
    private CheckBox brakeDam = new CheckBox();
    @FXML
    private CheckBox paintDam = new CheckBox();
    @FXML
    private CheckBox windshieldDam = new CheckBox();
    @FXML
    private CheckBox mirrorDam = new CheckBox();

    public void onSaveChangesButtonClicked() throws IOException, SQLException {
        dbCars db = new dbCars();
        String mods = this.mods.getText();
        String color = this.color.getText();
        String engine = this.engine.getText();
        String transmission = this.transmission.getText();
        boolean ac;
        boolean ps;
        boolean fl;
        boolean als;
        boolean engineDam, bodyDam, clutchDam, lampDam, brakeDam, paintDam, windshieldDam, mirrorDam;
        if(acYes.isSelected()){ac = true;}
        else {ac = false;}
        if(psYes.isSelected()){ps = true;}
        else{ps = false;}
        if(flYes.isSelected()){fl = true;}
        else{fl = false;}
        if(alsYes.isSelected()){als = true;}
        else{als = false;}
        int sunroof;
        if(sunroofYes.isSelected()){sunroof = 1;} else if (convertible.isSelected()) {sunroof = 2;}
        else{sunroof = 0;}
        if(this.bodyDam.isSelected()){bodyDam = true;}
        else{bodyDam = false;}
        if(this.engineDam.isSelected()){engineDam = true;}
        else{engineDam = false;}
        if(this.clutchDam.isSelected()){clutchDam = true;}
        else{clutchDam = false;}
        if(this.lampDam.isSelected()){lampDam = true;}
        else{lampDam = false;}
        if(this.brakeDam.isSelected()){brakeDam = true;}
        else{brakeDam = false;}
        if(this.paintDam.isSelected()){paintDam = true;}
        else{paintDam = false;}
        if(this.windshieldDam.isSelected()){windshieldDam = true;}
        else {windshieldDam = false;}
        if(this.mirrorDam.isSelected()){mirrorDam=true;}
        else{mirrorDam=false;}
        db.insertCarFeatures(color, engine, transmission, sunroof, mods, ac, ps, fl, als, bodyDam, engineDam, clutchDam, lampDam, brakeDam, paintDam, windshieldDam, mirrorDam);
        alert.success("Successfully added to database!");
    }
    public void onGoBackClicked() throws IOException {
        close();
        login.openHomepage();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("carFeatures.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 587);
        Stage stage = new Stage();
        stage.setTitle("Driftstore");
        stage.setScene(scene);
        stage.show();
    }
    public void close() throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.hide();
        loginController login = new loginController();
        login.openHomepage();
    }
}
