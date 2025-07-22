package com.example.theaterbooking.controller;

import com.example.theaterbooking.model.Theater;
import com.example.theaterbooking.service.TheaterLayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterLayoutService theaterSvc;

    @GetMapping
    public List<Theater> allActive() {
        return theaterSvc.listActive();
    }
    @GetMapping("/{id}/layout")
    public ResponseEntity<Theater> fullLayout(@PathVariable Long id) {
        Theater t = theaterSvc.getFullLayout(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Theater create(@RequestBody Theater theater) {
        return theater; // placeholder
    }
}
