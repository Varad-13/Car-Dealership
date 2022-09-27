package com.miniProject.carDealership;

import java.io.IOException;
import java.sql.*;

public class dbCars {
    private static final String Insert_Car = "INSERT INTO carDetails (manufacturer, model, registrationNumber, price, yearOfManufacture, chassisNumber, sellerEmail) Values (?, ?, ?, ?, ?, ?, ?)";
    dbUser jdbc = new dbUser();

    public void insertCarDetails(String man, String mod, String reg, int pri, int year, String chassis) throws IOException {
        try (Connection connection = jdbc.connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(Insert_Car)) {
            preparedStatement.setString(1, man);
            preparedStatement.setString(2, mod);
            preparedStatement.setString(3, reg);
            preparedStatement.setInt(4, pri);
            preparedStatement.setInt(5, year);
            preparedStatement.setString(6, chassis);
            preparedStatement.setString(7, jdbc.email);
            preparedStatement.executeUpdate();
            System.out.println("Added to database");
        } catch (SQLException e) {
            jdbc.printSQLException(e);
        }
    }

}
