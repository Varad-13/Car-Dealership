package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class buyerController {
    @FXML
    private javafx.scene.control.Button exit;
    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageView2 = new ImageView();
    @FXML
    private ImageView imageView3 = new ImageView();
    @FXML
    private Button previous = new Button();
    @FXML
    private Button next = new Button();
    @FXML
    private Button listing1 = new Button();
    @FXML
    private Button listing2 = new Button();
    @FXML
    private Button listing3 = new Button();
    @FXML
    private Label man1 = new Label();
    @FXML
    private Label man2 = new Label();
    @FXML
    private Label man3 = new Label();
    @FXML
    private Label mod1 = new Label();
    @FXML
    private Label mod2 = new Label();
    @FXML
    private Label mod3 = new Label();
    int i;
    dbCars car = new dbCars();
    dbCarDisplay display = new dbCarDisplay();
    alertBoxController alert = new alertBoxController();
    buyCarController buy = new buyCarController();
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("userHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 303);
        Stage stage = new Stage();
        stage.setTitle("Driftstore");
        stage.setScene(scene);
        stage.show();
    }
    public void close(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.hide();
    }
    public void initialize() throws SQLException, IOException {
        this.i = 1;
        displaySellerImage();
        previous.setVisible(false);
    }
    public void displaySellerImage() throws SQLException, IOException {
        conversion img = new conversion();
        WritableImage image1 = img.getImage(display.getCar(i));
        WritableImage image2 = img.getImage(display.getCar(i+1));
        WritableImage image3 = img.getImage(display.getCar(i+2));
        if(image1!=null) {
            man1.setVisible(true);
            mod1.setVisible(true);
            man1.setText(dbCarDisplay.manufacturer);
            mod1.setText(dbCarDisplay.model);
            imageView1.setVisible(true);
            imageView1.setImage(image1);
        }
        else{
            man1.setVisible(false);
            man2.setVisible(false);
            man3.setVisible(false);
            mod1.setVisible(false);
            mod2.setVisible(false);
            mod3.setVisible(false);
            next.setVisible(false);
            imageView1.setVisible(false);
            imageView2.setVisible(false);
            imageView3.setVisible(false);
            listing1.setVisible(false);
            listing2.setVisible(false);
            listing3.setVisible(false);
            return;
        }
        if(image2!=null) {
            man2.setVisible(true);
            mod2.setVisible(true);
            man2.setText(dbCarDisplay.manufacturer);
            mod2.setText(dbCarDisplay.model);
            imageView2.setVisible(true);
            imageView2.setImage(image2);
        }
        else{
            man2.setVisible(false);
            man3.setVisible(false);
            mod2.setVisible(false);
            mod3.setVisible(false);
            next.setVisible(false);
            imageView2.setVisible(false);
            imageView3.setVisible(false);
            listing2.setVisible(false);
            listing3.setVisible(false);
            return;
        }
        if(image3!=null) {
            man3.setVisible(true);
            mod3.setVisible(true);
            man3.setText(dbCarDisplay.manufacturer);
            mod3.setText(dbCarDisplay.model);
            imageView3.setVisible(true);
            imageView3.setImage(image3);
        }
        else{
            man3.setVisible(false);
            mod3.setVisible(false);
            next.setVisible(false);
            imageView3.setVisible(false);
            listing3.setVisible(false);
        }
    }
    public void onListing1Clicked() throws SQLException, IOException {
        display.getCarDetails(car.getCarId(i));
        buy.launch();
    }
    public void onListing2Clicked() throws SQLException, IOException {
        display.getCarDetails(car.getCarId(i+1));
        buy.launch();
    }
    public void onListing3Clicked() throws SQLException, IOException {
        display.getCarDetails(car.getCarId(i+2));
        buy.launch();
    }
    @FXML
    public void onUserButtonClicked() throws IOException {
        close();
        userDetailsController user = new userDetailsController();
        user.launch();
    }
    @FXML
    public void onExitButtonClicked() throws IOException {
        alert.exit("Are you sure you want to exit?");
    }
    public void nextButtonClicked() throws SQLException, IOException {
        previous.setVisible(true);
        i+=3;
        displaySellerImage();
    }
    public void previousButtonClicked() throws SQLException, IOException {
        if(i==4){
            next.setVisible(true);
            previous.setVisible(false);
            i-=3;
            displaySellerImage();
        }
        if(i>4) {
            next.setVisible(true);
            i -= 3;
            displaySellerImage();
        }
        else{
            displaySellerImage();
        }
    }

}
