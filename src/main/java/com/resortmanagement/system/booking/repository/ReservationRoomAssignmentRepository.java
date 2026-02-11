package com.resortmanagement.system.booking.repository;

import java.util.UUID;

import com.resortmanagement.system.booking.entity.ReservationRoomAssignment;
import com.resortmanagement.system.common.repository.SoftDeleteRepository;

public interface ReservationRoomAssignmentRepository extends SoftDeleteRepository<ReservationRoomAssignment, UUID> {

}
