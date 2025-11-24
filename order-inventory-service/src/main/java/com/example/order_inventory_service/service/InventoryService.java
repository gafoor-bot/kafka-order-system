package com.example.order_inventory_service.service;

import com.example.common.model.Order;
import com.example.order_inventory_service.model.Inventory;
import com.example.order_inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Check if inventory is available for the order.
     * Uses Redis cache to improve performance.
     * 
     * @param order The order to check inventory for
     * @return true if inventory is available, false otherwise
     */
    @Cacheable(value = "inventory", key = "#order.productId")
    public boolean checkInventory(Order order) {
        String productId = order.getProductId();
        int requiredQuantity = order.getQuantity();

        System.out.println("🔍 Checking inventory for product: " + productId + " (required: " + requiredQuantity + ")");

        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);

        if (inventoryOpt.isEmpty()) {
            System.out.println("⚠️ Product not found in inventory: " + productId);
            return false;
        }

        Inventory inventory = inventoryOpt.get();
        int availableQuantity = inventory.getAvailableQuantity();

        boolean inStock = availableQuantity >= requiredQuantity;

        if (inStock) {
            System.out.println("✅ Inventory check - Product: " + productId + 
                             ", Available: " + availableQuantity + 
                             ", Required: " + requiredQuantity);
        } else {
            System.out.println("❌ Insufficient inventory - Product: " + productId + 
                             ", Available: " + availableQuantity + 
                             ", Required: " + requiredQuantity);
        }

        return inStock;
    }

    /**
     * Reserve inventory for an order.
     * Evicts cache to ensure fresh data on next read.
     * 
     * @param order The order to reserve inventory for
     * @return true if reservation was successful, false otherwise
     */
    @Transactional
    @CacheEvict(value = "inventory", key = "#order.productId")
    public boolean reserveInventory(Order order) {
        String productId = order.getProductId();
        int requiredQuantity = order.getQuantity();

        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);

        if (inventoryOpt.isEmpty()) {
            return false;
        }

        Inventory inventory = inventoryOpt.get();
        int availableQuantity = inventory.getAvailableQuantity();

        if (availableQuantity < requiredQuantity) {
            return false;
        }

        // Reserve the quantity
        inventory.setReservedQuantity(inventory.getReservedQuantity() + requiredQuantity);
        inventoryRepository.save(inventory);

        System.out.println("🔒 Reserved inventory - Product: " + productId + 
                         ", Reserved: " + requiredQuantity);

        return true;
    }

    /**
     * Get inventory for a product (cached).
     * 
     * @param productId The product ID
     * @return Optional Inventory
     */
    @Cacheable(value = "inventory", key = "#productId")
    public Optional<Inventory> getInventory(String productId) {
        return inventoryRepository.findByProductId(productId);
    }

    /**
     * Update inventory (evicts cache).
     * 
     * @param inventory The inventory to update
     */
    @Transactional
    @CacheEvict(value = "inventory", key = "#inventory.productId")
    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        System.out.println("💾 Updated inventory - Product: " + inventory.getProductId());
    }
}

