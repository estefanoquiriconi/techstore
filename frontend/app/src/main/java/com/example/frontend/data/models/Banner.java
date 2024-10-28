package com.example.frontend.data.models;

public class Banner {
    private int id;
    private String image_url;

    public Banner() {
    }

    public Banner(int id, String image_url) {
        this.id = id;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
