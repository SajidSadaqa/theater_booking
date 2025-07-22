package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter @NoArgsConstructor
@IdClass(EventSeat.PK.class)
@Table(indexes = @Index(columnList = "event_id,status"))
public class EventSeat {
    @Id @ManyToOne(optional = false) private Event event;
    @Id @ManyToOne(optional = false) private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status = SeatStatus.AVAILABLE;

    @Getter @Setter @NoArgsConstructor
    public static class PK implements java.io.Serializable {
        private Long event;
        private Long seat;
    }
}
