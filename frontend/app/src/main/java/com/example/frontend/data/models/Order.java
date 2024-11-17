package com.example.frontend.data.models;

public class Order {

    private int id;
    private double total_amount;
    private String status;
    private String created_at;

    private int user_id;

    public Order() {
    }

    public Order(double total_amount, String status, String created_at, int user_id) {
        this.total_amount = total_amount;
        this.status = status;
        this.created_at = created_at;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
