package com.example.theaterbooking.repository;

import com.example.theaterbooking.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventSeatRepository extends JpaRepository<EventSeat, EventSeat.PK> {
    List<EventSeat> findByEventIdAndStatus(Long eventId, SeatStatus status);

    @Modifying
    @Query("""
        UPDATE EventSeat es
        SET es.status = :status
        WHERE es.event.id = :eventId AND es.seat.id IN :seatIds
    """)
    int bulkUpdateStatus(Long eventId, List<Long> seatIds, SeatStatus status);

    List<EventSeat> findByEventIdAndSeatIdIn(Long eventId, List<Long> seatIds);
}
