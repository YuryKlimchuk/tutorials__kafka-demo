package com.hydroyura.tutorials.models;

public class Order {

    private String userId, product;
    private Long productCount, status;


    public Order() {}


    public String getUserId() {
        return userId;
    }

    public Order setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public Order setProduct(String product) {
        this.product = product;
        return this;
    }

    public Long getProductCount() {
        return productCount;
    }

    public Order setProductCount(Long productCount) {
        this.productCount = productCount;
        return this;
    }

    public Long getStatus() {
        return status;
    }

    public Order setStatus(Long status) {
        this.status = status;
        return this;
    }
}
