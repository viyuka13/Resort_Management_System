package com.resortmanagement.system.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.room.entity.RoomAmenity;

@Repository
public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, Long> {
    // TODO: add custom queries if needed
}
