package com.example.theaterbooking.service;

import com.example.theaterbooking.model.EventSeat;
import com.example.theaterbooking.model.SeatStatus;
import com.example.theaterbooking.repository.EventSeatRepository;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
@RequiredArgsConstructor @Transactional(readOnly = true)

public class SeatAvailabilityService {
    public final EventSeatRepository eventSeatRepository;

    @Cacheable(value = "Seat", key = "#id")
    public List<EventSeat> availableSeats(Long eventId){
        return eventSeatRepository.findByEventIdAndStatus(eventId, SeatStatus.AVAILABLE);
    }
}
