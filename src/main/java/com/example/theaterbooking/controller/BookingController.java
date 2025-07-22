package com.example.theaterbooking.controller;

import com.example.theaterbooking.model.Booking;
import com.example.theaterbooking.service.BookingService;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingSvc;

    record BookingRequest(Long customerId, Long eventId, List<Long> seatIds) {}

    @PostMapping
    public Booking create(@RequestBody BookingRequest req) {
        return bookingSvc.createBooking(req.customerId(), req.eventId(), req.seatIds());
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        bookingSvc.cancelBooking(id);
    }
}

