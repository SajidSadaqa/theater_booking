package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uq_row_section_rownumber",
                columnNames = {"section_id","rowNumber"})
)
public class Row {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Section section;

    private int rowNumber;

    private int seatCount;

    @Column(nullable = false, length = 30)
    private String category;
}
