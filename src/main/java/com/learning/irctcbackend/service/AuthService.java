package com.learning.irctcbackend.service;

import com.learning.irctcbackend.dto.JwtAuthResponseDto;
import com.learning.irctcbackend.dto.LoginDto;
import com.learning.irctcbackend.dto.RegisterDto;
import com.learning.irctcbackend.model.Users;

public interface AuthService {
    JwtAuthResponseDto login(LoginDto loginDto);

    Users register(RegisterDto registerDto);
}
