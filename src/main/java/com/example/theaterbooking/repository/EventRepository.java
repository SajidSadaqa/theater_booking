package com.example.theaterbooking.repository;

import com.example.theaterbooking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository   extends JpaRepository<Event, Long> {}
