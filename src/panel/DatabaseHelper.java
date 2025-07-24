/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panel;

import panel.MenuItem;
import panel.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nguyen Van Chien
 */
public class DatabaseHelper {
    public List<MenuItem> getMenuItemsFromDatabase() {
        List<MenuItem> menuItems = new ArrayList<>();

        // Sử dụng lớp ConnectSQL để kết nối
        Connection conn = ConnectSQL.getConnection();
        
        if (conn != null) {
            try {
                // Truy vấn SQL để lấy danh sách món ăn
                String query = "SELECT id, name, price, category_id, imageUrl FROM menu_item";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Long categoryId = rs.getObject("category_id") != null ? rs.getLong("category_id") : null;
                    String imageUrl = rs.getString("imageUrl");

                    // Tạo đối tượng MenuItem và thêm vào danh sách
                    MenuItem item = new MenuItem(id, name, price, categoryId, imageUrl);
                    menuItems.add(item);
                }

                // Đóng kết nối
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return menuItems;
    }
}
