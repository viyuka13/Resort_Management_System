package com.resortmanagement.system.booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.booking.dto.request.AddOnSelectionRequest;
import com.resortmanagement.system.booking.dto.request.ReservationCreateRequest;
import com.resortmanagement.system.booking.dto.request.ReservationUpdateRequest;
import com.resortmanagement.system.booking.dto.response.ReservationDetailResponse;
import com.resortmanagement.system.booking.dto.response.ReservationResponse;
import com.resortmanagement.system.booking.entity.Reservation;
import com.resortmanagement.system.booking.entity.ReservationAddOn;
import com.resortmanagement.system.room.entity.RoomType;
import com.resortmanagement.system.booking.entity.BookingGuest;
import com.resortmanagement.system.booking.mapper.ReservationMapper;
import com.resortmanagement.system.booking.repository.ReservationAddOnRepository;
import com.resortmanagement.system.booking.repository.ReservationRepository;
import com.resortmanagement.system.booking.repository.BookingGuestRepository;
import com.resortmanagement.system.booking.repository.ReservationDailyRateRepository;
import com.resortmanagement.system.common.enums.AddOnStatus;
import com.resortmanagement.system.common.exception.ApplicationException;
import com.resortmanagement.system.common.guest.GuestRepository;
import com.resortmanagement.system.common.guest.Guest;
import com.resortmanagement.system.pricing.service.PricingQuoteService;
import com.resortmanagement.system.room.repository.RoomTypeRepository;
import com.resortmanagement.system.pricing.repository.RatePlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationAddOnRepository reservationAddOnRepository;
    private final PricingQuoteService pricingQuoteService;
    private final RoomTypeRepository roomTypeRepository;
    private final RatePlanRepository ratePlanRepository;
    private final BookingGuestRepository bookingGuestRepository;
    private final ReservationDailyRateRepository reservationDailyRateRepository;
    private final GuestRepository guestRepository;

    public ReservationDetailResponse createReservation(ReservationCreateRequest request) {
        // 1. Validation
        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new ApplicationException("Start date must be before end date");
        }

        // 2. Resolve RatePlan
        if (request.getRatePlanId() == null) {
            throw new ApplicationException("Rate Plan ID is required");
        }
        com.resortmanagement.system.pricing.entity.RatePlan ratePlan = ratePlanRepository
                .findById(request.getRatePlanId())
                .orElseThrow(() -> new ApplicationException("Rate Plan not found"));

        // 3. Availability Check
        UUID roomTypeId = ratePlan.getRoomTypeId();
        if (roomTypeId == null) {
            if (request.getRoomTypeId() != null) {
                roomTypeId = request.getRoomTypeId();
            } else {
                throw new ApplicationException("Rate Plan is not linked to a Room Type and no Room Type provided");
            }
        }

        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ApplicationException("Room Type not found"));

        long overlapping = reservationRepository.countOverlappingReservations(roomType.getId(), request.getStartDate(),
                request.getEndDate());
        if (roomType.getTotalKeys() != null && overlapping >= roomType.getTotalKeys()) {
            throw new ApplicationException("No availability for the selected dates");
        }

        // 4. Create Entity
        Reservation reservation = ReservationMapper.toEntity(request);
        reservation.setRatePlan(ratePlan);

        // Set Booker (Primary Guest) linked to Reservation
        if (request.getGuestId() != null) {
            Guest booker = guestRepository.findById(request.getGuestId())
                    .orElseThrow(() -> new ApplicationException("Guest (Booker) not found"));
            reservation.setGuestId(booker);
        } else {
            throw new ApplicationException("Guest ID (Booker) is required");
        }

        // 5. Pricing Calculation
        com.resortmanagement.system.pricing.dto.request.PricingQuoteRequest quoteReq = new com.resortmanagement.system.pricing.dto.request.PricingQuoteRequest();
        com.resortmanagement.system.pricing.dto.request.GuestPricingInput input = new com.resortmanagement.system.pricing.dto.request.GuestPricingInput();
        input.setRatePlanId(ratePlan.getId());
        input.setCheckIn(request.getStartDate());
        input.setCheckOut(request.getEndDate());
        input.setNumberOfGuests(request.getNumGuests());
        quoteReq.setGuestInputs(List.of(input));

        com.resortmanagement.system.pricing.dto.response.PricingQuoteResponse quote = pricingQuoteService
                .calculate(quoteReq);

        // Save Reservation first to get ID
        reservationRepository.save(reservation);

        // 6. Save Daily Rates
        if (quote.getGuestBreakdowns() != null && !quote.getGuestBreakdowns().isEmpty()) {
            var breakdown = quote.getGuestBreakdowns().get(0);
            for (var day : breakdown.getDailyBreakdown()) {
                com.resortmanagement.system.booking.entity.ReservationDailyRate dailyRate = new com.resortmanagement.system.booking.entity.ReservationDailyRate();
                dailyRate.setReservationId(reservation);
                dailyRate.setDate(day.getDate());
                dailyRate.setAmount(day.getFinalPrice());
                dailyRate.setRatePlanId(ratePlan);
                reservationDailyRateRepository.save(dailyRate);
            }
        }

        // 7. Save Guests (Occupants)
        if (request.getBookingGuests() != null) {
            for (var guestReq : request.getBookingGuests()) {
                if (guestReq.getGuestId() == null)
                    continue;

                Guest occupant = guestRepository.findById(guestReq.getGuestId())
                        .orElseThrow(
                                () -> new ApplicationException("Occupant Guest not found: " + guestReq.getGuestId()));

                BookingGuest bookingGuest = new BookingGuest();
                bookingGuest.setReservationId(reservation);
                bookingGuest.setGuestId(occupant);
                bookingGuest.setPrimary(guestReq.getIsPrimary() != null ? guestReq.getIsPrimary() : false);
                bookingGuest.setGuestType(guestReq.getGuestType());
                bookingGuest.setAge(guestReq.getAge());
                bookingGuest.setSpecialNeeds(guestReq.getSpecialNeeds());

                bookingGuestRepository.save(bookingGuest);
            }
        }

        // Handle optional add-ons
        if (request.getAddOns() != null) {
            for (AddOnSelectionRequest sel : request.getAddOns()) {
                ReservationAddOn addOn = new ReservationAddOn();
                addOn.setReservationId(reservation);
                addOn.setAddOnCode("ADDON_" + sel.getAddOnId());
                addOn.setQuantity(sel.getQuantity());
                addOn.setStatus(AddOnStatus.REQUESTED);
                reservationAddOnRepository.save(addOn);
            }
        }

        return ReservationMapper.toDetailResponse(reservation);
    }

    @Transactional(readOnly = true)
    public ReservationDetailResponse getReservation(UUID id) {
        Reservation reservation = reservationRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException("Reservation not found"));
        return ReservationMapper.toDetailResponse(reservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> listReservations() {
        return reservationRepository.findByDeletedFalse()
                .stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    public void updateReservation(UUID id, ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException("Reservation not found"));
        ReservationMapper.updateEntity(reservation, request);
        reservationRepository.save(reservation);
    }

    public void cancelReservation(UUID id) {
        Reservation reservation = reservationRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException("Reservation not found"));
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
    }
}
