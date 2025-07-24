/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhtuananmodel;

import Ennum.TransactionType;
import connect.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO chuyên trách các thao tác với bảng ingredient,
 * bao gồm: CRUD thông thường, importIngredient, exportIngredient.
 */
public class IngredientDAO {

    private final IngredientHistoryDAO historyDAO = new IngredientHistoryDAO();

    
    private static final String SELECT_BY_ID_SQL =
        "SELECT id, import_price, name, quantity_in_stock, unit FROM ingredient WHERE id = ?";

    private static final String UPDATE_QUANTITY_SQL =
        "UPDATE ingredient SET quantity_in_stock = ? WHERE id = ?";
    
    private static final String DELETE_INGREDIENT_BY_ID_SQL =
    "DELETE FROM ingredient WHERE id = ?";

   private static final String DELETE_HISTORY_BY_INGREDIENT_ID_SQL =
        "DELETE FROM ingredient_history WHERE ingredient_id = ?";


    public Ingredient getIngredientByName(String name) throws SQLException {
    String sql = "SELECT id, import_price, name, quantity_in_stock, unit FROM ingredient WHERE name = ?";
    Ingredient ingredient = null;

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                ingredient = new Ingredient();
                ingredient.setId(rs.getLong("id"));
                ingredient.setImportPrice(rs.getBigDecimal("import_price"));
                ingredient.setName(rs.getString("name"));
                ingredient.setQuantityInStock(rs.getDouble("quantity_in_stock"));
                ingredient.setUnit(rs.getString("unit"));
            }
        }
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ingredient;
}

    
    /**
     * Lấy thông tin Ingredient theo ID.
     */
    public Ingredient getIngredientById(Long id) throws SQLException {
        Ingredient ingredient = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ingredient = new Ingredient();
                    ingredient.setId(rs.getLong("id"));
                    ingredient.setImportPrice(rs.getBigDecimal("import_price"));                 
                    ingredient.setName(rs.getString("name"));
                    ingredient.setQuantityInStock(rs.getDouble("quantity_in_stock"));
                    ingredient.setUnit(rs.getString("unit"));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredient;
    }

    /**
     * Cập nhật số lượng tồn của Ingredient (chỉ số lượng).
     */
    private void updateQuantity(Long ingredientId, Double newQuantity, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_QUANTITY_SQL)) {
            ps.setDouble(1, newQuantity);
            ps.setLong(2, ingredientId);
            ps.executeUpdate();
        }
    }

    /**
     * Xử lý "Nhập kho" cho một nguyên liệu:
     *  1. Lấy Ingredient hiện tại
     *  2. Tính số lượng mới = quantity_in_stock + quantityToAdd
     *  3. Cập nhật vào bảng ingredient
     *  4. Ghi vào bảng ingredient_history với transaction_type = IMPORT
     *
     * @param ingredientId    ID của nguyên liệu
     * @param quantityToAdd   Số lượng muốn nhập (phải > 0)
     * @param note            Ghi chú (có thể null)
     * @throws SQLException   nếu có lỗi thao tác DB hoặc nguyên liệu không tồn tại
     */
    public void importIngredientByName(String name, BigDecimal importPrice, String unit, double quantityToAdd, String note) throws SQLException {
    if (quantityToAdd <= 0) {
        throw new IllegalArgumentException("Số lượng nhập phải > 0");
    }

    Connection conn = null;
    try {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        // 1. Tìm nguyên liệu theo tên
        Ingredient ingredient = getIngredientByName(name);
        Long ingredientId;
        double newQty;

        if (ingredient == null) {
            // Nếu chưa có, tạo mới nguyên liệu
            ingredientId = insertIngredient(name, importPrice, quantityToAdd, unit, conn);
            newQty = quantityToAdd;
        } else {
            // Nếu đã có, cập nhật số lượng tồn kho
            ingredientId = ingredient.getId();
            double oldQty = ingredient.getQuantityInStock();
            newQty = oldQty + quantityToAdd;
            updateQuantity(ingredientId, newQty, conn);
        }

        // 2. Tạo lịch sử nhập kho
        IngredientHistory history = new IngredientHistory();
        history.setIngredientId(ingredientId);
        history.setQuantity(quantityToAdd);
        history.setTransactionType(TransactionType.IMPORT);
        history.setTransactionDate(LocalDateTime.now());
        history.setNote(note);
        history.setPriceAtTransaction(importPrice);
        history.setStockAfterTransaction(newQty);

        // 3. Ghi lịch sử
        historyDAO.insertHistory(history,conn);

        conn.commit();
    } catch (SQLException ex) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        throw ex;
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }
}

    private Long insertIngredient(String name, BigDecimal importPrice, double quantity, String unit, Connection conn) throws SQLException {
    String sql = "INSERT INTO ingredient (name, import_price, quantity_in_stock, unit) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, name);
        ps.setBigDecimal(2, importPrice);
        ps.setDouble(3, quantity);
        ps.setString(4, unit);
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                long newId = rs.getLong(1);
               
                // --- Ghi lịch sử nhập kho sau khi tạo mới nguyên liệu ---
                IngredientHistory history = new IngredientHistory();
                history.setIngredientId(newId);
                history.setQuantity(quantity);
                history.setTransactionType(TransactionType.IMPORT);
                history.setTransactionDate(LocalDateTime.now());
                history.setNote("Nhập kho lần đầu");
                history.setPriceAtTransaction(importPrice);
                history.setStockAfterTransaction(quantity);

                historyDAO.insertHistory(history,conn);

                return newId;
            } else {
                throw new SQLException("Không thể lấy ID sau khi thêm nguyên liệu.");
            }
        }
    }
}


    /**
     * Xử lý "Xuất kho" cho một nguyên liệu:
     *  1. Lấy Ingredient hiện tại
     *  2. Kiểm tra quantityToRemove <= quantity_in_stock
     *  3. Tính số lượng mới = quantity_in_stock - quantityToRemove
     *  4. Cập nhật vào bảng ingredient
     *  5. Ghi vào bảng ingredient_history với transaction_type = EXPORT
     *
     * @param ingredientId      ID của nguyên liệu
     * @param quantityToRemove  Số lượng muốn xuất (phải > 0)
     * @param note              Ghi chú (có thể null)
     * @throws SQLException     nếu có lỗi thao tác DB hoặc không đủ hàng để xuất
     */
    public void exportIngredientByName(String name, double quantityToRemove, String note) throws SQLException {
    if (quantityToRemove <= 0) {
        throw new IllegalArgumentException("Số lượng xuất phải > 0");
    }

    Connection conn = null;
    try {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        // 1. Tìm nguyên liệu theo tên
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient == null) {
            throw new SQLException("Không tìm thấy nguyên liệu với tên: " + name);
        }

        Long ingredientId = ingredient.getId();
        double oldQty = ingredient.getQuantityInStock();

        // 2. Kiểm tra tồn kho đủ để xuất
        if (quantityToRemove > oldQty) {
            throw new SQLException("Không đủ hàng để xuất. Tồn kho hiện tại: " + oldQty);
        }

        double newQty = oldQty - quantityToRemove;

        // 3. Cập nhật lại số lượng tồn kho
        updateQuantity(ingredientId, newQty, conn);
            

        // 4. Ghi lịch sử xuất kho
        IngredientHistory history = new IngredientHistory();
        history.setIngredientId(ingredientId);
        history.setQuantity(quantityToRemove);
        history.setTransactionType(TransactionType.EXPORT);
        history.setTransactionDate(LocalDateTime.now());
        history.setNote(note);
        history.setPriceAtTransaction(ingredient.getImportPrice()); // giá lúc nhập gần nhất
        history.setStockAfterTransaction(newQty);

        historyDAO.insertHistory(history,conn);

        conn.commit();
    } catch (SQLException ex) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        throw ex;
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }
}

        private void deleteIngredientById(Long ingredientId, Connection conn) throws SQLException {
            try (PreparedStatement ps = conn.prepareStatement(DELETE_INGREDIENT_BY_ID_SQL)) {
                ps.setLong(1, ingredientId);
                ps.executeUpdate();
            }
        }

        public void deleteIngredientWithHistory(Long ingredientId) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Xóa lịch sử liên quan
            try (PreparedStatement psHistory = conn.prepareStatement(DELETE_HISTORY_BY_INGREDIENT_ID_SQL)) {
                psHistory.setLong(1, ingredientId);
                psHistory.executeUpdate();
            }

            // 2. Xóa bản ghi trên bảng ingredient
            try (PreparedStatement psIng = conn.prepareStatement(DELETE_INGREDIENT_BY_ID_SQL)) {
                psIng.setLong(1, ingredientId);
                psIng.executeUpdate();               
            }

            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
            }
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
