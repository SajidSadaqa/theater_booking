package com.example.theaterbooking.service;

import com.example.theaterbooking.model.*;
import com.example.theaterbooking.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final EventRepository        eventRepo;
    private final SeatRepository         seatRepo;
    private final CustomerRepository     customerRepo;
    private final EventSeatRepository    eventSeatRepo;
    private final BookingRepository      bookingRepo;
    private final BookingSeatRepository  bookingSeatRepo;


    @Transactional
    public Booking createBooking(Long customerId,
                                 Long eventId,
                                 List<Long> seatIds) {

        if (seatIds == null || seatIds.isEmpty())
            throw new IllegalArgumentException("Seat list must not be empty.");


        List<EventSeat> eventSeats =
                eventSeatRepo.findByEventIdAndSeatIdIn(eventId, seatIds);

        if (eventSeats.size() != seatIds.size())
            throw new IllegalStateException("Some seats do not exist for this event.");

        for (EventSeat es : eventSeats) {
            if (es.getStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("Seat " + es.getSeat().getLabel() + " is not available.");
            }
            es.setStatus(SeatStatus.BOOKED);
        }
        eventSeatRepo.saveAll(eventSeats);

        Booking booking = new Booking();
        booking.setEvent   (eventRepo   .getReferenceById(eventId));
        booking.setCustomer(customerRepo.getReferenceById(customerId));
        booking = bookingRepo.save(booking);

        Booking finalBooking = booking;
        List<BookingSeat> bookingSeats = seatIds.stream()
                .map(id -> {
                    BookingSeat bs = new BookingSeat();
                    bs.setBooking(finalBooking);
                    bs.setSeat(seatRepo.getReferenceById(id));
                    return bs;
                })
                .toList();
        bookingSeatRepo.saveAll(bookingSeats);

        return booking;
    }

    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        if (booking.getStatus() == BookingState.CANCELLED) return;

        List<Long> seatIds = bookingSeatRepo.findSeatIdsByBookingId(bookingId);

        List<EventSeat> eventSeats =
                eventSeatRepo.findByEventIdAndSeatIdIn(booking.getEvent().getId(), seatIds);

        eventSeats.forEach(es -> es.setStatus(SeatStatus.AVAILABLE));
        eventSeatRepo.saveAll(eventSeats);

        booking.setStatus(BookingState.CANCELLED);
        bookingRepo.save(booking);
    }
    
}
