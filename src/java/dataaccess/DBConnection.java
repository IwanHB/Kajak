package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Iwan
 */
public class DBConnection {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/kajakudlejning";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn = null;
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while(rs.next()){
            int id = rs.getInt("userid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(id+":"+username+":"+password);
        }
    }
    
}
