package loginapp;

import DatabaseConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for login data collected from the user
 */
public class LoginModel {
    Connection connection;
    public LoginModel(){
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

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String login, String password, String isAdmin) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "SELECT * from LOGIN_DATA where LOGIN = ? and PASSWD = ? and ADM = ?";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setString(1, login);
            pr.setString(2, password);
            pr.setString(3, isAdmin);

            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(pr != null && rs != null) {
                pr.close();
                rs.close();
            }
        }
        return false;
    }

}
