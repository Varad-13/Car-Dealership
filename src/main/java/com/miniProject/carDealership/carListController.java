package com.miniProject.carDealership;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sql.RowSet.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

import static com.miniProject.carDealership.dbUser.email;

public class carListController {
    loginController login = new loginController();
    @FXML
    private javafx.scene.control.Button goBack;
    @FXML
    private TextField manufacturer;
    @FXML
    private TextField model;
    @FXML
    private TextField registrationNumber;
    @FXML
    private TextField price;
    @FXML
    private TextField chassisNumber;
    @FXML
    private TextField yearOfManufacture;
    @FXML
    private ImageView imageView1 = new ImageView();
    @FXML
    private ImageView imageView2 = new ImageView();
    @FXML
    private ImageView imageView3 = new ImageView();
    private static FileInputStream picture1 = null;
    private static FileInputStream picture2 = null;
    private static FileInputStream picture3 = null;
    private File file1 = new File("image1.jpg");
    private File file2 = new File("image2.jpg");
    private File file3 = new File("image3.jpg");
    private static int count = 0;
    private static int length1;
    private static int length2;
    private static int length3;
    @FXML
    public void onGoBackClicked() throws IOException {
        picture1 = null;
        picture2 = null;
        picture3 = null;
        count = 0;
        close();
        login.openHomepage();
    }
    public void initialize() {
        this.count = 0;
    }


    public void onConfirmButtonClicked() throws IOException, SQLException {
        carFeatureController feature = new carFeatureController();
        dbCars carDb = new dbCars();
        String man = manufacturer.getText();
        String mod = model.getText();
        String reg = registrationNumber.getText();
        int pri = Integer.parseInt(price.getText());
        int year = Integer.parseInt(yearOfManufacture.getText());
        String chassis = chassisNumber.getText();
        carDb.insertCarDetails(man, mod, reg, pri, year, chassis, picture1, picture2, picture3, length1, length2, length3);
        //carDb.getCarId(1, email);
        close();
        feature.launch();
    }
    public void launch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(driver.class.getResource("carList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 614, 597);
        Stage stage = new Stage();
        stage.setTitle("Driftstore");
        stage.setScene(scene);
        stage.show();
    }
    public void close(){
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.hide();
    }
    public void upload() throws IOException {
        count = count + 1;
        Stage stage = new Stage();
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(stage);
        if(file==null){
            alertBoxController alert = new alertBoxController();
            alert.generalError("Please choose a picture");
        }
        if(file!=null) {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedImage, null);
            switch (count) {
                case 1:
                    imageView1.setImage(image);
                    this.picture1 = new FileInputStream(file);
                    this.length1 = (int) file.length();
                    break;
                case 2:
                    imageView2.setImage(image);
                    this.picture2 = new FileInputStream(file);
                    this.length2 = (int) file.length();
                    break;
                case 3:
                    imageView3.setImage(image);
                    this.picture3 = new FileInputStream(file);
                    this.length3 = (int) file.length();
                    break;
            }
        }
    }
}
