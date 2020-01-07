package com.productfeed;

public class Product {
    int id;
    String name;
    float price;
    Long timestamp;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getTimestamp() {
        return this.timestamp;
    }
}
