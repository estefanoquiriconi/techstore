package com.example.frontend.data.models;

public class Product {

    private int id;
    private String name;
    private String price;
    private String description;
    private String image_url;
    private int stock;
    private boolean active;

    public Product() {
    }

    public Product(int id, String name, String price, String description, String imageUrl, int stock, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image_url = imageUrl;
        this.stock = stock;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
