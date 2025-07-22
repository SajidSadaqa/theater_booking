package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- relations ---------- */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theater_id")          // FK column name in DB
    private Theater theater;

    /* ---------- simple fields ---------- */

    @Column(name = "section_name", nullable = false, length = 128)
    private String sectionName;

    @Column(name = "section_order")           // “order” is a keyword; be explicit
    private Integer sectionOrder;

    /** X offset (left‑right) of the section, in theatre units */
    @Column(name = "position_x", nullable = false)
    private Double positionX = 0.0;

    /** Y offset (front‑back) of the section, in theatre units */
    @Column(name = "position_y", nullable = false)
    private Double positionY = 0.0;

    /** Rotation in degrees, clockwise from stage centre */
    @Column(name = "rotation_angle", nullable = false)
    private Double rotationAngle = 0.0;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "section")
    private List<Row> rows;
}
