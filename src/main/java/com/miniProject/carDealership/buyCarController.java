package com.miniProject.carDealership;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class buyCarController {
    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageView2 = new ImageView();
    @FXML
    private ImageView imageView3 = new ImageView();
    @FXML
    private Text manufacturer = new Text();
    @FXML
    private Text model = new Text();
    @FXML
    private TextField price = new TextField();
    @FXML
    private TextField mods = new TextField();
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
    dbCarDisplay display = new dbCarDisplay();
    dbCars cars = new dbCars();
    alertBoxController alert = new alertBoxController();
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("buyCar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 869, 765);
        Stage stage = new Stage();
        stage.setTitle("Driftstore");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() throws IOException {
        conversion img = new conversion();
        WritableImage image1 = img.getImage(dbCarDisplay.image1);
        WritableImage image2 = img.getImage(dbCarDisplay.image2);
        WritableImage image3 = img.getImage(dbCarDisplay.image3);
        imageView1.setImage(image1);
        imageView2.setImage(image2);
        imageView3.setImage(image3);
        manufacturer.setText(dbCarDisplay.manufacturer);
        model.setText(dbCarDisplay.model);
        price.setText(String.valueOf(dbCarDisplay.price));
        if(dbCarDisplay.ac){acYes.setSelected(true);}
        if(dbCarDisplay.powerSteering){psYes.setSelected(true);}
        if(dbCarDisplay.sunroof == 1){sunroofYes.setSelected(true);}
        if(dbCarDisplay.sunroof == 2){convertible.setSelected(true);}
        if(dbCarDisplay.fogLamps){flYes.setSelected(true);}
        if(dbCarDisplay.antiLockBraking){alsYes.setSelected(true);}
        if(dbCarDisplay.bodyDam){bodyDam.setSelected(true);}
        if(dbCarDisplay.engineDam){engineDam.setSelected(true);}
        if(dbCarDisplay.paintDam){paintDam.setSelected(true);}
        if(dbCarDisplay.clutchDam){clutchDam.setSelected(true);}
        if(dbCarDisplay.lampDam){lampDam.setSelected(true);}
        if(dbCarDisplay.brakeDam){brakeDam.setSelected(true);}
        if(dbCarDisplay.windowDam){windshieldDam.setSelected(true);}
        if(dbCarDisplay.mirrorDam){mirrorDam.setSelected(true);}
    }
    public void close() throws IOException {
        Stage stage = (Stage) flYes.getScene().getWindow();
        stage.hide();
    }

    public void buy() throws SQLException, IOException {
        dbCars cars = new dbCars();
        if(cars.buy()){
            alert.success("Congratulations on your purchase");
        }
    }
}
