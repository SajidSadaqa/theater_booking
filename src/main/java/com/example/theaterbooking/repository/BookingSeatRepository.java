package com.example.theaterbooking.repository;


import com.example.theaterbooking.model.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
    @Query("select bs.seat.id from BookingSeat bs where bs.booking.id = :bookingId")
    List<Long> findSeatIdsByBookingId(Long bookingId);
}