package com.example.frontend.data.models;

public class OrderDetail {
    private String price;
    private int quantity;
    private String subtotal;
    private int product_id;
    private Product Product;

    public OrderDetail(int productId, int quantity){
        this.product_id = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return product_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        this.Product = product;
    }
}
