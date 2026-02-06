package com.resortmanagement.system.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.room.entity.RoomBlock;

@Repository
public interface RoomBlockRepository extends JpaRepository<RoomBlock, Long> {
    // TODO: add custom queries if needed
}
