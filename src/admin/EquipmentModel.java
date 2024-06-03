package admin;

import DatabaseConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for adding new equipments to the database
 */
public class EquipmentModel {
    Connection connection;
    private Integer classNumber;
    private String model;
    private String type;

    public EquipmentModel() {
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

    public Integer getclassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean validateClassNumber() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "SELECT * from ROOMS where ROOM_ID = ?";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setInt(1, this.classNumber);
            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }
        return false;
    }

    public void addEquipment() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "INSERT INTO equipments VALUES(NULL, ?, ?, ?)";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setInt(1, this.classNumber);
            pr.setString(2, this.type);
            pr.setString(3, this.model);
            rs = pr.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }
    }
}
