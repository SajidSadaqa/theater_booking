package com.example.theaterbooking.repository;

import com.example.theaterbooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository    extends JpaRepository<Seat, Long> {}
