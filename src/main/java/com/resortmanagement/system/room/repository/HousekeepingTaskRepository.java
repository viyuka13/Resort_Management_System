package com.resortmanagement.system.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.room.entity.HousekeepingTask;
@Repository
public interface HousekeepingTaskRepository extends JpaRepository<HousekeepingTask, Long> {
    // TODO: add custom queries if needed
}
