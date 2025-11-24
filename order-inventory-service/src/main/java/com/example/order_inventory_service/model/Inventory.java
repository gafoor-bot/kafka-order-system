package com.example.order_inventory_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private String productId;
    private int quantity;
    private int reservedQuantity;

    // Default constructor (required by JPA)
    public Inventory() {
    }

    // All-args constructor
    public Inventory(String productId, int quantity, int reservedQuantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public int getAvailableQuantity() {
        return quantity - reservedQuantity;
    }

    // toString method for logging
    @Override
    public String toString() {
        return "Inventory{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", reservedQuantity=" + reservedQuantity +
                ", availableQuantity=" + getAvailableQuantity() +
                '}';
    }
}

