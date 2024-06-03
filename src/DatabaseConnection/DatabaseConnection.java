package DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * class responsible for connecting to the database
 */
public class DatabaseConnection {
    private static final String USERNAME = "z19";
    private static final String PASSWORD = "ycpsjw";
    private static final String CNAME = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName(CNAME);
            return DriverManager.getConnection(URL, USERNAME,PASSWORD);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
