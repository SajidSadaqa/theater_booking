package com.example.theaterbooking.controller;

import com.example.theaterbooking.auth.JwtUtil;
import com.example.theaterbooking.auth.Role;
import com.example.theaterbooking.auth.User;
import com.example.theaterbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        if (userRepo.findByUsername(dto.username()).isPresent())
            return ResponseEntity.badRequest().body("username exists");

        User u = User.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .roles(Set.of(dto.role()))
                .build();
        userRepo.save(u);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        Authentication auth =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        User u = (User) auth.getPrincipal();
        String token = jwtUtil.generateToken(u.getUsername(), u.getRoles());

        return ResponseEntity.ok(new TokenDto(token));
    }


    public record RegisterDto(String username, String password, Role role) {}
    public record LoginDto(String username, String password)                {}
    public record TokenDto(String token)                                    {}
}