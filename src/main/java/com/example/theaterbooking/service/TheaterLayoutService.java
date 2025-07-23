package com.example.theaterbooking.service;

import com.example.theaterbooking.model.Theater;
import com.example.theaterbooking.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TheaterLayoutService {

    private final TheaterRepository theaterRepo;

    public List<Theater> listActive() {
        return theaterRepo.findByIsActiveTrue();
    }

    @Cacheable(value = "theaters", key = "#id")
    public Theater getFullLayout(Long theaterId) {
        return theaterRepo.fetchFullLayout(theaterId);
    }

}
