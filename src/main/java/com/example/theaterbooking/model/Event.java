package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Event {

    /* ---------- PK ---------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- FK ---------- */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theater_id", nullable = false)          // FK column in events table
    private Theater theater;

    /* ---------- Event details ---------- */

    @Column(name = "event_name", nullable = false, length = 120)
    private String eventName;

    @Column(name = "event_description", columnDefinition = "text")
    private String eventDescription;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event_time", nullable = false)
    private LocalTime eventTime;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "base_price",
            nullable = false,
            precision = 10,   // 8 digits + 2 decimals
            scale = 2)
    private BigDecimal basePrice;

    /* ---------- Relationships ---------- */

    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings;
}
