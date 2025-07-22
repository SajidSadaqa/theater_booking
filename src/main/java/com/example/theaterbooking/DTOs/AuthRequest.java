package com.example.theaterbooking.DTOs;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
