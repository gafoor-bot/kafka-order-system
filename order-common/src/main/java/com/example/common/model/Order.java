package com.example.common.model;

import java.math.BigDecimal;

public class Order {
    private String orderId;
    private String productId;
    private int quantity;
    private BigDecimal amount;
    private String status;

    public Order() {}

    public Order(String orderId, String productId, int quantity, BigDecimal amount, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    // Getters and setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

