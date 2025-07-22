package com.example.theaterbooking.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TheaterLayoutDTO(
        @NotBlank String name,
        @NotBlank String city,
        @NotEmpty List<SectionSpec> layout
) {
    public record SectionSpec(
            @NotBlank String code,
            @NotEmpty List<@Min(1) Integer> rows
    ) {}
}
