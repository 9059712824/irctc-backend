package com.learning.irctcbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String createdTime;

    private String updatedTime;
}
