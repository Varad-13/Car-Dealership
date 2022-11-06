package com.miniProject.carDealership;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbCarDisplay {
    dbUser jdbc = new dbUser();
    private static final String Get_Features = "SELECT * FROM carDetails WHERE idcarDetails = ? AND sold = 0";
    private static final String Retrive_Car = "SELECT * FROM carDetails WHERE sellerEmail = ? AND sold = 0 ORDER BY idcarDetails DESC";
    private static final String get_car = "SELECT * FROM carDetails WHERE sold = 0 ORDER BY idcarDetails DESC";
    public static String manufacturer;
    private static String chassisNumber;
    private static String registrationNumber;
    protected static String model;
    public static int price;
    public static InputStream image1;
    protected static InputStream image2;
    protected static InputStream image3;
    public static String sellerEmail;
    public static int yearOfManufacture;
    public static String color;
    public static String engine;
    public static String transmission;
    public static String mods;
    public static int sunroof;
    public static Boolean ac;
    public static Boolean powerSteering;
    public static Boolean fogLamps;
    public static Boolean antiLockBraking;
    protected static Boolean bodyDam;
    public static Boolean engineDam;
    public static Boolean clutchDam;
    public static Boolean lampDam;
    public static Boolean brakeDam;
    public static Boolean paintDam;
    public static Boolean windowDam;
    public static Boolean mirrorDam;
    public static int carId;
    public void getCarDetails(int idcarDetails) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Get_Features)) {
            preparedStatement.setInt(1, idcarDetails);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                this.manufacturer = rs.getString("manufacturer");
                this.model = rs.getString("model");
                this.chassisNumber = rs.getString("chassisNumber");
                this.registrationNumber = rs.getString("registrationNumber");
                this.price = rs.getInt("price");
                this.image1 = rs.getBinaryStream("image1");
                this.image2 = rs.getBinaryStream("image2");
                this.image3 = rs.getBinaryStream("image3");
                this.sellerEmail = rs.getString("sellerEmail");
                this.yearOfManufacture = rs.getInt("yearOfManufacture");
                this.color = rs.getString("color");
                this.engine = rs.getString("engine");
                this.transmission = rs.getString("transmission");
                this.mods = rs.getString("mods");
                this.sunroof = rs.getInt("sunroof");
                this.ac = rs.getBoolean("ac");
                this.powerSteering = rs.getBoolean("powerSteering");
                this.fogLamps = rs.getBoolean("fogLamps");
                this.antiLockBraking = rs.getBoolean("antiLockBraking");
                this.bodyDam = rs.getBoolean("bodyDam");
                this.engineDam = rs.getBoolean("engineDam");
                this.clutchDam = rs.getBoolean("clutchDam");
                this.lampDam = rs.getBoolean("lampDam");
                this.brakeDam = rs.getBoolean("brakeDam");
                this.paintDam = rs.getBoolean("paintDam");
                this.windowDam = rs.getBoolean("windowDam");
                this.mirrorDam = rs.getBoolean("mirrorDam");
                this.carId = rs.getInt("idCarDetails");
            }
        }
    }
    public InputStream retriveCar(int i, String email) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Retrive_Car)) {
            preparedStatement.setString(1, email);
            ResultSet carData = preparedStatement.executeQuery();
            while(i>0){
                if(carData.next()){
                    if(i==1){
                        System.out.println(carData.getInt("idcarDetails"));
                        InputStream file = carData.getBinaryStream("image1");
                        manufacturer = carData.getString("manufacturer");
                        model = carData.getString("model");
                        this.price = carData.getInt("price");
                        return file;
                    }
                }
                i--;
            }
            return null;
        }
    }
    public InputStream getCar(int i) throws IOException, SQLException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(get_car)) {
            ResultSet carData = preparedStatement.executeQuery();
            while(i>0){
                if(carData.next()){
                    if(i==1){
                        System.out.println(carData.getInt("idcarDetails"));
                        InputStream file = carData.getBinaryStream("image1");
                        manufacturer = carData.getString("manufacturer");
                        model = carData.getString("model");
                        this.price = carData.getInt("price");
                        return file;
                    }
                }
                i--;
            }
        }
        return null;
    }
}
