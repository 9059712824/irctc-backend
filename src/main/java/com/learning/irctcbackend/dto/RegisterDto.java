package com.learning.irctcbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String confirmPassword;
}
