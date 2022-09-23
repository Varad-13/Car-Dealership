package com.miniProject.carDealership;
import java.io.IOException;
import java.sql.*;


public class dbUser {
    private static final String DATABASE_URL = "jdbc:mysql://database-1.cj5xtfkwqhiq.ap-south-1.rds.amazonaws.com/mini_project";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "genius123";
    private static final String INSERT_LOGIN = "INSERT INTO loginDetails (uname, uemail, upasswd) VALUES (?, ?, MD5(?))";
    private static final String INSERT_DATA = "INSERT INTO userData (uname, uemail, uadd, unum, utype, uDob, upincode) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CHECK_CREDENTIALS = "SELECT * FROM loginDetails WHERE uemail = ? and upasswd = MD5(?)";
    private static final String CHECK_USERTYPE = "SELECT utype FROM userData WHERE uemail = ?";
    static String email = null;
    static int usertype = 4;
    public Connection connectDatabase() throws IOException {
        try{
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch(SQLException e){
            alertBoxController alert = new alertBoxController();
            System.out.println("SQL Connection fail!");
            alert.generalError("Can't connect to internet");
        }
        return null;
    }
    public boolean checkConnection() {
        try{
            connectDatabase();
            System.out.println("Connection Success");
            return true;
        } catch(IOException e){
            return false;
        }
    }

    public boolean checkCredentials(String email, String passwd) throws SQLException, IOException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CREDENTIALS)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login Success");
                dbUser.email = email;
                return true;
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public int checkUsertype(){
        if(dbUser.usertype == 4) {
            try (Connection connection = connectDatabase();
                 PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERTYPE)) {
                preparedStatement.setString(1, dbUser.email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt("utype"));
                    dbUser.usertype = resultSet.getInt("utype");
                    return usertype;
                } else
                    return 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return dbUser.usertype;
        }
    }
    public void insertLoginDetails(String name, String email, String passwd) throws SQLException, IOException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwd);
            preparedStatement.executeUpdate();
            System.out.println("User registration successful");
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUserData(String name, String email, String address, String number, int type, Date dob, int pincode) throws SQLException, IOException {
        alertBoxController alert = new alertBoxController();
        try (Connection connection = connectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, number);
            preparedStatement.setInt(5, type);
            preparedStatement.setDate(6, dob);
            preparedStatement.setInt(7, pincode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (IOException e) {
            alert.generalError("Runtime Error!");
            throw new RuntimeException(e);
        }
    }

    public static void printSQLException(SQLException e) throws IOException {
        alertBoxController alert = new alertBoxController();
        alert.generalError("SQL Error!");
    }
}
