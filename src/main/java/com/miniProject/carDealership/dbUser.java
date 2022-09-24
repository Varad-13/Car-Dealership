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
    private static final String UPDATE_DATA = "UPDATE userData SET uadd = ?, unum = ?, utype = ?, upincode = ? WHERE uemail = ?";
    private static final String GET_USER_DETAILS = "SELECT * FROM userData WHERE uemail = ?";
    static String email = null;
    static int usertype = 4;
    static String name = null;
    static String address = null;
    static String number = null;
    static int pincode;
    static Date dob;
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
    public int checkUsertype() throws IOException {
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
            }
        }
        else {
            return dbUser.usertype;
        }
    }
    public void insertLoginDetails(String name, String email, String passwd) throws IOException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, passwd);
            preparedStatement.executeUpdate();
            System.out.println("User registration successful");
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertUserData(String name, String email, String address, String number, int type, Date dob, int pincode) throws IOException {
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
        }
    }
    public void updateUserDetails(String address, String number, int type, int pincode) throws SQLException, IOException {
        try (Connection connection = connectDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATA)){
            System.out.println("data sent to sql");
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, number);
            preparedStatement.setInt(3, type);
            preparedStatement.setInt(4, pincode);
            preparedStatement.setString(5, dbUser.email);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            getUserDetails();
        }
    }
    public void getUserDetails() throws SQLException, IOException {
        try (Connection connection = connectDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_DETAILS)) {
            preparedStatement.setString(1, dbUser.email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dbUser.name = resultSet.getString("uname");
                dbUser.address = resultSet.getString("uadd");
                dbUser.number = resultSet.getString("unum");
                dbUser.dob = resultSet.getDate("uDob");
                dbUser.pincode = resultSet.getInt("upincode");
                dbUser.usertype = resultSet.getInt("utype");
            }
        }
    }

    public static void printSQLException(SQLException e) throws IOException {
        alertBoxController alert = new alertBoxController();
        alert.generalError("SQL Error! "+e);
    }
}
