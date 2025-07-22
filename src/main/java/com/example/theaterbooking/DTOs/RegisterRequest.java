package com.example.theaterbooking.DTOs;

import com.example.theaterbooking.auth.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;

    private Role role = Role.USER;
}
