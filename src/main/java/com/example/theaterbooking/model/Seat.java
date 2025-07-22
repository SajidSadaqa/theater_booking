package com.example.theaterbooking.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter @NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name="uq_seat_row_index",  columnNames={"row_id","seatIndex"}),
                @UniqueConstraint(name="uq_seat_label",      columnNames={"label"})
        }
)
public class Seat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Row row;

    private int seatIndex;

    @Column(nullable = false, length = 20)
    private String label;
}
