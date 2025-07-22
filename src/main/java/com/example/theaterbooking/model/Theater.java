package com.example.theaterbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "theaters")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "theater_type", nullable = false)
    private TheaterType theaterType;

    @Column(name = "total_capacity")
    private Integer totalCapacity = 0;

    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private List<Section> sections;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    public enum TheaterType { CIRCULAR, RECTANGULAR, CUSTOM }
}
