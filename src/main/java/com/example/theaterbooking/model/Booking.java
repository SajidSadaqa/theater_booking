package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;

@Entity
@Getter
@Setter @NoArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Event event;
    @ManyToOne(optional = false) private Customer customer;

    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingState status = BookingState.CONFIRMED;

}

