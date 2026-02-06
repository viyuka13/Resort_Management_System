/*
TODO: FNB repository & service guidelines
Repositories:
 - extend JpaRepository
 - provide queries for active items, menu lookups

Services:
 - OrderService.confirmOrder must:
    * be @Transactional
    * check ingredient availability
    * perform atomic decrement (UPDATE ... WHERE quantity_on_hand >= :qty)
    * insert InventoryTransaction rows with sourceType=ORDER and sourceId=order.id
 - MenuItemService to manage menu items and update recipe.

File: fnb/repository/<File>.java, fnb/service/<File>.java
*/
package com.resortmanagement.system.fnb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.fnb.entity.ActivityEvent;
import com.resortmanagement.system.fnb.repository.ActivityEventRepository;

@Service
public class ActivityEventService {

    private final ActivityEventRepository repository;

    public ActivityEventService(ActivityEventRepository repository) {
        this.repository = repository;
    }

    public List<ActivityEvent> findAll() {
        // TODO: add pagination and filtering
        return repository.findAll();
    }

    public Optional<ActivityEvent> findById(Long id) {
        // TODO: add caching and error handling
        return repository.findById(id);
    }

    public ActivityEvent save(ActivityEvent entity) {
        // TODO: add validation and business rules
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        // TODO: add soft delete if required
        repository.deleteById(id);
    }
}
