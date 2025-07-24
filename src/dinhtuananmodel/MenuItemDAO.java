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
import java.util.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemDAO {
    
    public MenuItem getMenuItemById(long id) {
    String sql = "SELECT * FROM menu_item WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setLong(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getLong("category_id"),
                    rs.getString("imageUrl")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}

    
    public  List<MenuItem> getMenuItemsByCategory(long categoryId) {
        List<MenuItem> list = new ArrayList<>();
        String sql = "SELECT * FROM menu_item WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setLong(1, categoryId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                long id        = rs.getLong("id");
                String name    = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                long catId     = rs.getLong("category_id");
                String imgUrl  = rs.getString("imageUrl");
                list.add(new MenuItem(id, name, price, catId, imgUrl));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> list = new ArrayList<>();
        String sql = "SELECT * FROM menu_item";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getLong("category_id"),
                    rs.getString("imageUrl")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean insertMenuItem(String name, BigDecimal price, long categoryId, String imageURL) {
        String sql = "INSERT INTO menu_item(name, price, category_id, imageUrl) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            ps.setLong(3, categoryId);
            ps.setString(4,imageURL);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMenuItem(long id, String name, BigDecimal price, long categoryId,String imageURL) {
        String sql = "UPDATE menu_item SET name=?, price=?, category_id=?,imageUrl=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            ps.setLong(3, categoryId);
            ps.setString(4, imageURL);
            ps.setLong(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteMenuItem(long id) {
        String sql = "DELETE FROM menu_item WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public boolean deleteMenuItemsByCategoryId(long categoryId) {
        String sql = "DELETE FROM menu_item WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, categoryId);
            int affected = ps.executeUpdate();
            // Nếu affected >= 0 (dù có xóa được hay không), vẫn trả về true (không coi là lỗi)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
 * Tìm các MenuItem có tên chứa nameKeyword (không phân biệt hoa thường).
 */
public List<MenuItem> searchMenuItemsByName(String nameKeyword) {
    List<MenuItem> list = new ArrayList<>();
    String sql = "SELECT id, name, price, category_id, imageUrl "
               + "FROM menu_item "
               + "WHERE LOWER(name) LIKE LOWER(?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, "%" + nameKeyword + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getLong("category_id"),
                    rs.getString("imageUrl")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
}

/**
 * Tìm các MenuItem thuộc những Category có tên chứa categoryNameKeyword (không phân biệt hoa thường).
 */
public List<MenuItem> searchMenuItemsByCategoryName(String categoryNameKeyword) {
    List<MenuItem> list = new ArrayList<>();
    String sql = "SELECT m.id, m.name, m.price, m.category_id, m.imageUrl "
               + "FROM menu_item m "
               + "JOIN category c ON m.category_id = c.id "
               + "WHERE LOWER(c.name) LIKE LOWER(?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, "%" + categoryNameKeyword + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getLong("category_id"),
                    rs.getString("imageUrl")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
}

/**
 * Tìm các MenuItem có giá bằng đúng priceValue.
 */
public List<MenuItem> searchMenuItemsByPrice(BigDecimal priceValue) {
    List<MenuItem> list = new ArrayList<>();
    String sql = "SELECT id, name, price, category_id, imageUrl "
               + "FROM menu_item "
               + "WHERE price >= ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setBigDecimal(1, priceValue);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MenuItem(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getLong("category_id"),
                    rs.getString("imageUrl")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
}

    
}

