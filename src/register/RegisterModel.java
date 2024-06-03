package register;

import DatabaseConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for adding new user to the database
 */
public class RegisterModel {
    Connection connection;
    private String login;
    private String name;
    private String surname;
    private String password;
    private String passwordCheck;

    public RegisterModel(){
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(this.connection == null) {
            System.out.println("Failed to connect to DB: Exit 1");
            System.exit(1);
        }
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean validateLogin() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "SELECT * from LOGIN_DATA where LOGIN = ?";
        boolean result = false;
        try {
            pr = this.connection.prepareStatement(query);
            pr.setString(1, this.login);
            rs = pr.executeQuery();
            result = !rs.next();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(pr != null && rs != null) {
                pr.close();
                rs.close();
            }
        }
        return result;
    }
    public boolean validatePasswords(){
        return this.password.equals(this.passwordCheck);
    }

    public boolean validatePasswordStrength(){
        return this.password.length() >= 6;
    }

    private void addLogin() throws SQLException{
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "INSERT INTO login_data VALUES(?, ?, ?)";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setString(1, this.login);
            pr.setString(2, this.password);
            pr.setString(3, "N");
            rs = pr.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(pr != null && rs != null) {
                pr.close();
                rs.close();
            }
        }
    }

    private void addUser() throws SQLException{
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "INSERT INTO usrs VALUES(NULL, ?, ?, ?, ?)";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setString(1, this.login);
            pr.setString(2, this.name);
            pr.setString(3, this.surname);
            pr.setString(4, this.password);
            rs = pr.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(pr != null && rs != null) {
                pr.close();
                rs.close();
            }
        }
    }

    public void registerUser(){
        try {
            this.addLogin();
            this.addUser();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
