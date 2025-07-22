package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "sections")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Section {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    private String sectionName;
    private Integer sectionOrder;
    private Double positionX = 0.0;
    private Double positionY = 0.0;
    private Double rotationAngle = 0.0;
    private Boolean isActive = true;

}
