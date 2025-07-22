package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter @NoArgsConstructor
@IdClass(BookingSeat.PK.class)
public class BookingSeat {
    @Id @ManyToOne(optional = false) private Booking booking;
    @Id @ManyToOne(optional = false) private Seat seat;

    @Getter @Setter @NoArgsConstructor
    public static class PK implements java.io.Serializable {
        private Long booking;
        private Long seat;
    }
}

