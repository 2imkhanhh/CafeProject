package connect;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cafe_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String User = "root";
    private static final String Pass ="";
    public static Connection getConnection() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,User,Pass);
            System.out.println("Kết nối thành công");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;  
    }
    public static void Close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
