package com.example.frontend.data.models;

public class Review {
    private int id;
    private String comment;
    private int rating;
    private String created_at;

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }
    public String getCreatedAt() {
        return created_at;
    }

}
