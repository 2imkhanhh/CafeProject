/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhtuananmodel;

/**
 *
 * @author admin
 */
import connect.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CategoryDAO {

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Category(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertCategory(String name) {
        if (isCategoryNameExists(name)) {
        JOptionPane.showMessageDialog(null, "Tên danh mục đã tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return false;
    }
        
        String sql = "INSERT INTO category(name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCategory(long id, String name) {
        if (isCategoryNameExists(name)) {
        JOptionPane.showMessageDialog(null, "Tên danh mục đã tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        return false;
    }
        String sql = "UPDATE category SET name=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setLong(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteCategory(long id) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // 1) Xóa các menu_item trước:
            String sqlDelItems = "DELETE FROM menu_item WHERE category_id = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlDelItems)) {
                ps1.setLong(1, id);
                ps1.executeUpdate();
            }

            // 2) Xóa category:
            String sqlDelCat = "DELETE FROM category WHERE id = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlDelCat)) {
                ps2.setLong(1, id);
                int affectedCat = ps2.executeUpdate();
                if (affectedCat == 0) {
                    // Nếu không xóa được category (ví dụ id không tồn tại), rollback và trả về false
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
        return false;
    }
    
    public boolean isCategoryNameExists(String name) {
    String sql = "SELECT COUNT(*) FROM category WHERE name = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return false;
}

  
public List<Category> searchCategoriesByName(String keyword) {
    List<Category> list = new ArrayList<>();
    // Dùng LOWER để tìm không phân biệt hoa thường
    String sql = "SELECT id, name FROM category WHERE LOWER(name) LIKE LOWER(?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Thêm ký tự % để tìm gần đúng
        ps.setString(1, "%" + keyword + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Category(
                    rs.getLong("id"),
                    rs.getString("name")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
}

    
}