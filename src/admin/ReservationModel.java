package admin;

import DatabaseConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Auxiliary class for adding new reservations to the database
 */
public class ReservationModel {
    Connection connection;
    private Integer rClassNumber;
    private String date;
    private Integer hourS;
    private Integer hourE;
    private Integer userId;

    public ReservationModel() {
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

    public Integer getRClassNumber() {
        return rClassNumber;
    }

    public void setRClassNumber(Integer classNumber) {
        this.rClassNumber = classNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHourS() {
        return hourS;
    }

    public void setHourS(Integer hourS) {
        this.hourS = hourS;
    }

    public Integer getHourE() {
        return hourE;
    }

    public void setHourE(Integer hourE) {
        this.hourE = hourE;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public void addReservation() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String dateS;
        String dateE;

        String query = "INSERT INTO RESERVATIONS VALUES(NULL, ?, ?, ?, ?)";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setInt(1, this.userId);
            pr.setInt(2, this.rClassNumber);
            dateS = this.date + " " + Integer.toString(this.hourS) + ":00:00";
            dateE = this.date + " " + Integer.toString(this.hourE) + ":00:00";
            pr.setTimestamp(3, Timestamp.valueOf(dateS));
            pr.setTimestamp(4, Timestamp.valueOf(dateE));
            rs = pr.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }
    }

    public boolean validateReservation() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String query = "SELECT room_id, TO_CHAR(reservation_start, 'HH24') as start_hour, TO_CHAR(reservation_end, 'HH24') as end_hour FROM RESERVATIONS WHERE ROOM_ID = ?  and  TO_CHAR(reservation_start, 'YYYY-MM-DD') = ?";
        try{
            pr = this.connection.prepareStatement(query);
            pr.setInt(1, this.rClassNumber);
            pr.setString(2, this.date);
            rs = pr.executeQuery();
            while(rs.next()){
                if ((this.hourS < rs.getInt("end_hour") && this.hourE > rs.getInt("start_hour")) || (this.hourE > rs.getInt("start_hour") && this.hourS < rs.getInt("end_hour"))) {
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pr.close();
            rs.close();
        }
        return true;
    }

    public boolean validateClassNumber() throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String query = "SELECT * from ROOMS where ROOM_ID = ?";
        try {
            pr = this.connection.prepareStatement(query);
            pr.setInt(1, this.rClassNumber);
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

}
