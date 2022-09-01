package com.varad.carDealership;
import java.io.IOException;
import java.sql.*;


public class db {
    private static final String DATABASE_URL = "jdbc:mysql://database-1.cj5xtfkwqhiq.ap-south-1.rds.amazonaws.com/mini_project";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "genius123";
    private static final String INSERT_LOGIN = "INSERT INTO loginDetails (uname, uemail, upasswd) VALUES (?, ?, MD5(?))";
    private static final String INSERT_DATA = "INSERT INTO userData (uname, uemail, uadd) VALUES (?, ?, ?)";
    private static final String CHECK_CREDENTIALS = "SELECT * FROM loginDetails WHERE uemail = ? and upasswd = MD5(?)";

    public boolean checkCredentials(String email, String passwd) throws SQLException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CREDENTIALS)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void insertLoginDetails(String name, String email, String passwd) throws SQLException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwd);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUserData(String name, String email, String address) throws SQLException{
        try (Connection connection = connectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkConnection() throws IOException {
        try{
            DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            System.out.println("Connection Success");
            return true;
        } catch(SQLException e){
            System.out.println("SQL Connection fail!");
            alertBoxController alert = new alertBoxController();
            alert.generalError();
            return false;
        }
    }
    public Connection connectDatabase() throws IOException {
        try{
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch(SQLException e){
            System.out.println("SQL Connection fail!");
            alertBoxController alert = new alertBoxController();
            alert.generalError();
        }
        return null;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
