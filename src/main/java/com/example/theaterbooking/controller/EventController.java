package com.example.theaterbooking.controller;

import com.example.theaterbooking.model.EventSeat;
import com.example.theaterbooking.service.SeatAvailabilityService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final SeatAvailabilityService availabilitySvc;

    @GetMapping("/{id}/seats")
    public List<EventSeat> seats(@PathVariable Long id) {
        return availabilitySvc.availableSeats(id);
    }
}
