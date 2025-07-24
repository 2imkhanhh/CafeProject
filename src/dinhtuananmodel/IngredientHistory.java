/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhtuananmodel;

import Ennum.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class IngredientHistory {
    private Long id;                    // id chính
    private String note;                // ghi chú (có thể null)
    private BigDecimal priceAtTransaction;  // giá tại thời điểm giao dịch
    private Double quantity;            // số lượng đã nhập/xuất
    private LocalDateTime transactionDate; // thời gian giao dịch
    private TransactionType transactionType; // IMPORT  hoặc EXPORT
    private Long ingredientId;          // khóa ngoại tới ingredient.id
    private Double stockAfterTransaction; // số lượng tồn sau giao dịch

    

    public IngredientHistory() { }

    public IngredientHistory(Long id, String note, BigDecimal priceAtTransaction, Double quantity,
                             LocalDateTime transactionDate, TransactionType transactionType,
                             Long ingredientId, Double stockAfterTransaction) {
        this.id = id;
        this.note = note;
        this.priceAtTransaction = priceAtTransaction;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.ingredientId = ingredientId;
        this.stockAfterTransaction = stockAfterTransaction;
    }

    public IngredientHistory(String note, BigDecimal priceAtTransaction, Double quantity,
                             LocalDateTime transactionDate, TransactionType transactionType,
                             Long ingredientId, Double stockAfterTransaction) {
        this(null, note, priceAtTransaction, quantity, transactionDate, transactionType, ingredientId, stockAfterTransaction);
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getPriceAtTransaction() {
        return priceAtTransaction;
    }

    public void setPriceAtTransaction(BigDecimal priceAtTransaction) {
        this.priceAtTransaction = priceAtTransaction;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Double getStockAfterTransaction() {
        return stockAfterTransaction;
    }

    public void setStockAfterTransaction(Double stockAfterTransaction) {
        this.stockAfterTransaction = stockAfterTransaction;
    }

    @Override
    public String toString() {
        return "IngredientHistory{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", priceAtTransaction=" + priceAtTransaction +
                ", quantity=" + quantity +
                ", transactionDate=" + transactionDate +
                ", transactionType=" + transactionType +
                ", ingredientId=" + ingredientId +
                ", stockAfterTransaction=" + stockAfterTransaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientHistory)) return false;
        IngredientHistory that = (IngredientHistory) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(note, that.note) &&
               Objects.equals(priceAtTransaction, that.priceAtTransaction) &&
               Objects.equals(quantity, that.quantity) &&
               Objects.equals(transactionDate, that.transactionDate) &&
               transactionType == that.transactionType &&
               Objects.equals(ingredientId, that.ingredientId) &&
               Objects.equals(stockAfterTransaction, that.stockAfterTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, priceAtTransaction, quantity,
                            transactionDate, transactionType, ingredientId, stockAfterTransaction);
    }
}