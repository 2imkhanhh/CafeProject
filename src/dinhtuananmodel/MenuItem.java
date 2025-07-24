/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dinhtuananmodel;

/**
 *
 * @author admin
 */
import java.math.BigDecimal;

public class MenuItem {
    private long id;
    private String name;
    private BigDecimal price;
    private long categoryId;
    private String imageUrl;

    public MenuItem() {}

    public MenuItem(long id, String name, BigDecimal price, long categoryId,String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl=imageUrl;
    }
    public MenuItem(String name, BigDecimal price, long categoryId,String imageUrl) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl=imageUrl;
    }
    // getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public long getCategoryId() { return categoryId; }
    public void setCategoryId(long categoryId) { this.categoryId = categoryId; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

