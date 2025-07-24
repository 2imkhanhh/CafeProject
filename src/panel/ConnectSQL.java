package panel;

import cafe_project.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/cafe_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";
        
        try {
            conn = DriverManager.getConnection(url, user, password); // Thử kết nối đến cơ sở dữ liệu
        } catch (SQLException e) {
            e.printStackTrace();  // In thông tin lỗi nếu không kết nối được
            // Bạn có thể hiển thị thông báo hoặc thực hiện hành động khác tùy ý
        }
        
        return conn; // Trả về kết nối (null nếu không thành công)
    }
}
