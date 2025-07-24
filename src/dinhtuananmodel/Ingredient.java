/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhtuananmodel;

import java.math.BigDecimal;

/**
 *
 * @author admin
 */
public class Ingredient {
    private long id;
    private BigDecimal importPrice;
    private String name;
    private double quantityInStock;
    private String unit;

    public Ingredient() {
        // Constructor mặc định
    }

    public Ingredient(long id, BigDecimal importPrice, String name, double quantityInStock, String unit) {
        this.id = id;
        this.importPrice = importPrice;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.unit = unit;
    }

    // --- Getters và Setters ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", importPrice=" + importPrice +
                ", name='" + name + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", unit='" + unit + '\'' +
                '}';
    }
}
