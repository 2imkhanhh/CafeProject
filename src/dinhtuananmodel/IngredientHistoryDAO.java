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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * DAO chuyên trách các thao tác với bảng ingredient_history.
 */
public class IngredientHistoryDAO {

    // Câu SQL để thêm 1 bản ghi vào ingredient_history
    private static final String INSERT_HISTORY_SQL = 
        "INSERT INTO ingredient_history "
      + "(note, price_at_transaction, quantity, transaction_date, transaction_type, ingredient_id, stock_after_transaction) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Thêm một bản ghi lịch sử cho Ingredient.
     *
     * @param history   Đối tượng chứa thông tin để ghi vào bảng ingredient_history
     * @throws SQLException nếu có lỗi khi thao tác DB
     */
     public void insertHistory(IngredientHistory history, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_HISTORY_SQL)) {
            ps.setString(1, history.getNote());
            if (history.getPriceAtTransaction() != null) {
                ps.setBigDecimal(2, history.getPriceAtTransaction());
            } else {
                ps.setNull(2, java.sql.Types.DECIMAL);
            }
            ps.setDouble(3, history.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(history.getTransactionDate()));
            ps.setString(5, history.getTransactionType().name());
            ps.setLong(6, history.getIngredientId());
            ps.setDouble(7, history.getStockAfterTransaction());
            ps.executeUpdate();
        }
    }
}

