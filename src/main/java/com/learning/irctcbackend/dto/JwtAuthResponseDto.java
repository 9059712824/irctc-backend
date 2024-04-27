package com.learning.irctcbackend.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponseDto {

    private String accessToken;

    private String tokenType = "Bearer";

    private List<String> roles;
}
