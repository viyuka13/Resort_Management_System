/*
TODO: Inventory repository & service guidelines

Services:
 - InventoryTransactionService should:
    * provide method consumeIngredients(Map<itemId, qty>) that executes atomic updates (SQL update with WHERE qty >= required).
    * write InventoryTransaction rows
    * raise InsufficientInventoryException if any item cannot be consumed.
 - PurchaseOrderService to handle PO lifecycle.
File: inventory/repository/<File>.java, inventory/service/<File>.java
*/
package com.resortmanagement.system.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.inventory.entity.InventoryItem;
import com.resortmanagement.system.inventory.repository.InventoryItemRepository;

@Service
public class InventoryItemService {

    private final InventoryItemRepository repository;

    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<InventoryItem> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public InventoryItem save(InventoryItem entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
