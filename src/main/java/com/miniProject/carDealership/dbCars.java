package com.miniProject.carDealership;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class dbCars {
    private static final String Insert_Car = "INSERT INTO carDetails (manufacturer, model, registrationNumber, price, yearOfManufacture, chassisNumber, sellerEmail, image1, image2, image3) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String Retrive_Car = "SELECT * FROM carDetails WHERE sellerEmail = ? ORDER BY idcarDetails DESC";
    private static final String Update = "UPDATE carDetails SET price = ?, color = ?, engine = ?, transmission = ?, mods = ?, sunroof = ?, ac = ?, powerSteering = ?, fogLamps = ?, antiLockBraking = ?, bodyDam = ?, engineDam = ?, clutchDam = ?, lampDam = ?, windowDam = ?, brakeDam = ?, paintDam = ?, mirrorDam = ? WHERE idcarDetails = ?";
    private static final String get_car = "SELECT * FROM carDetails ORDER BY idcarDetails DESC";
    static dbUser jdbc = new dbUser();
    private static int carId;
    private static int price;
    private static String sellerEmail = jdbc.email;
    public void insertCarDetails(String man, String mod, String reg, int pri, int year, String chassis, FileInputStream image1, FileInputStream image2, FileInputStream image3, int length1, int length2, int length3) throws IOException {
        price = pri;
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Insert_Car)) {
            preparedStatement.setString(1, man);
            preparedStatement.setString(2, mod);
            preparedStatement.setString(3, reg);
            preparedStatement.setInt(4, pri);
            preparedStatement.setInt(5, year);
            preparedStatement.setString(6, chassis);
            preparedStatement.setString(7, jdbc.email);
            preparedStatement.setBinaryStream(8, image1, length1);
            preparedStatement.setBinaryStream(9, image2, length2);
            preparedStatement.setBinaryStream(10, image3, length3);
            preparedStatement.executeUpdate();
            try (Statement stmt = connection.prepareStatement(Retrive_Car)) {
                preparedStatement.setString(1, sellerEmail);
                ResultSet rs = stmt.executeQuery(Retrive_Car);
                if(rs.next())
                    this.carId = rs.getInt("idcarDetails");
            }
            System.out.println("Added to database");
        } catch (SQLException e) {
            jdbc.printSQLException(e);
        }
    }
    public void insertCarFeatures(String color, String engine, String transmission, int sunroof, String mods, Boolean ac, Boolean powerSteering, Boolean fogLamps, Boolean antiLockBraking, Boolean bodyDam, Boolean engineDam, Boolean clutchDam, Boolean lampDam, Boolean brakeDam, Boolean paintDam, Boolean windowDam, Boolean mirrorDam) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)){
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, engine);
            preparedStatement.setString(4, transmission);
            preparedStatement.setString(5, mods);
            preparedStatement.setInt(6, sunroof);
            preparedStatement.setBoolean(7, ac);
            preparedStatement.setBoolean(8, powerSteering);
            preparedStatement.setBoolean(9, fogLamps);
            preparedStatement.setBoolean(10, antiLockBraking);
            preparedStatement.setBoolean(11, bodyDam);
            preparedStatement.setBoolean(12, engineDam);
            preparedStatement.setBoolean(13, clutchDam);
            preparedStatement.setBoolean(14, lampDam);
            preparedStatement.setBoolean(15, brakeDam);
            preparedStatement.setBoolean(16, paintDam);
            preparedStatement.setBoolean(17, windowDam);
            preparedStatement.setBoolean(18, mirrorDam);
            preparedStatement.setInt(19, this.carId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }
    public void updateCarFeatures(int price, String color, String engine, String transmission, int sunroof, String mods, Boolean ac, Boolean powerSteering, Boolean fogLamps, Boolean antiLockBraking, Boolean bodyDam, Boolean engineDam, Boolean clutchDam, Boolean lampDam, Boolean brakeDam, Boolean paintDam, Boolean windowDam, Boolean mirrorDam) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Update)){
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, engine);
            preparedStatement.setString(4, transmission);
            preparedStatement.setString(5, mods);
            preparedStatement.setInt(6, sunroof);
            preparedStatement.setBoolean(7, ac);
            preparedStatement.setBoolean(8, powerSteering);
            preparedStatement.setBoolean(9, fogLamps);
            preparedStatement.setBoolean(10, antiLockBraking);
            preparedStatement.setBoolean(11, bodyDam);
            preparedStatement.setBoolean(12, engineDam);
            preparedStatement.setBoolean(13, clutchDam);
            preparedStatement.setBoolean(14, lampDam);
            preparedStatement.setBoolean(15, brakeDam);
            preparedStatement.setBoolean(16, paintDam);
            preparedStatement.setBoolean(17, windowDam);
            preparedStatement.setBoolean(18, mirrorDam);
            preparedStatement.setInt(19, this.carId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }
    public int getCarId(int i, String email) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Retrive_Car)) {
            preparedStatement.setString(1, email);
            ResultSet carData = preparedStatement.executeQuery();
            while(i>0){
                if(carData.next()){
                    if(i==1){
                        this.carId = carData.getInt("idcarDetails");
                        return carId;
                    }
                }
                i--;
            }
            return 0;
        }
    }
    public int getCarId(int i) throws SQLException, IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(get_car)) {
            ResultSet carData = preparedStatement.executeQuery();
            while(i>0){
                if(carData.next()){
                    if(i==1){
                        this.carId = carData.getInt("idcarDetails");
                        return carId;
                    }
                }
                i--;
            }
            return 0;
        }
    }
}
