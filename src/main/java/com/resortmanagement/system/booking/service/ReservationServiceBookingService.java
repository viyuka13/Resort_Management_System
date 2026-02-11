package com.resortmanagement.system.booking.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.booking.dto.request.ReservationServiceBookingRequest;
import com.resortmanagement.system.booking.dto.response.ReservationServiceBookingResponse;
import com.resortmanagement.system.booking.entity.Reservation;
import com.resortmanagement.system.booking.entity.ReservationServiceBooking;
import com.resortmanagement.system.booking.mapper.ReservationServiceBookingMapper;
import com.resortmanagement.system.booking.repository.ReservationRepository;
import com.resortmanagement.system.booking.repository.ReservationServiceBookingRepository;
import com.resortmanagement.system.common.exception.ApplicationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceBookingService {

    private final ReservationServiceBookingRepository repository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceBookingResponse bookService(
            UUID reservationId,
            ReservationServiceBookingRequest request) {

        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ApplicationException("Reservation not found"));

        ReservationServiceBooking booking =
            ReservationServiceBookingMapper.toEntity(request);
        booking.setReservationId(reservation);

        repository.save(booking);
        return ReservationServiceBookingMapper.toResponse(booking);
    }

    public void cancelServiceBooking(UUID serviceBookingId) {
        ReservationServiceBooking booking = repository.findByIdAndDeletedFalse(serviceBookingId)
            .orElseThrow(() -> new ApplicationException("Service booking not found"));
        booking.setDeleted(true);
        repository.save(booking);
    }
}
