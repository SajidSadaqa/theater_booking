package com.example.theaterbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.theaterbooking.repository") // keep
@EntityScan({
        "com.example.theaterbooking.model",
        "com.example.theaterbooking.auth"
})
public class TheaterBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheaterBookingApplication.class, args);
    }
}
