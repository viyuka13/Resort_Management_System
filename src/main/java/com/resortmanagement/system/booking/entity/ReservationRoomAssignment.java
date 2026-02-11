package com.resortmanagement.system.booking.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.resortmanagement.system.common.audit.AuditableSoftDeletable;
import com.resortmanagement.system.common.enums.RoomAssignmentStatus;
import com.resortmanagement.system.room.entity.Room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReservationRoomAssignment extends AuditableSoftDeletable {
    @Id
    @UuidGenerator
    @Column(name = "assigned_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservationId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    private RoomAssignmentStatus status;
}