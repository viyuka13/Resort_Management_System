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

package com.resortmanagement.system.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.inventory.entity.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    // TODO: add custom queries if needed
}
