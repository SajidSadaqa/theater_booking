package com.example.theaterbooking.service;

import com.example.theaterbooking.auth.JwtUtil;
import com.example.theaterbooking.auth.Role;
import com.example.theaterbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.theaterbooking.auth.User;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public void register(String username, String rawPassword, Role role) {
        if (repo.existsByUsername(username))
            throw new IllegalStateException("username already exists");

        User u = User.builder()
                .username(username)
                .password(encoder.encode(rawPassword))
                .roles(java.util.Set.of(role))
                .build();
        repo.save(u);
    }

    public String login(String username, String password) throws AuthenticationException {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        User u = repo.findByUsername(username).orElseThrow();
        return jwtUtil.generateToken(u.getUsername(), u.getRoles());
    }
}