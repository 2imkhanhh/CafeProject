/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panel;

import cafe_project.*;
import panel.*;


/**
 *
 * @author Nguyen Van Chien
 */
public class MenuItem {
    private long id;          // Thêm id của món ăn
    private String name;
    private double price;
    private Long categoryId; // category_id có thể là NULL, nên dùng kiểu Long
    private String imageUrl;

    // Constructor
    public MenuItem(long id, String name, double price, Long categoryId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
