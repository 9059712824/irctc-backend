package com.learning.irctcbackend.service.impl;

import com.learning.irctcbackend.dao.UserRoleDao;
import com.learning.irctcbackend.dao.UsersDao;
import com.learning.irctcbackend.dto.JwtAuthResponseDto;
import com.learning.irctcbackend.dto.LoginDto;
import com.learning.irctcbackend.dto.RegisterDto;
import com.learning.irctcbackend.exception.AlreadyFoundException;
import com.learning.irctcbackend.exception.InvalidInputException;
import com.learning.irctcbackend.model.RoleType;
import com.learning.irctcbackend.model.UserRole;
import com.learning.irctcbackend.model.Users;
import com.learning.irctcbackend.security.JwtTokenProvider;
import com.learning.irctcbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersDao usersDao;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleDao userRoleDao;

    @Override
    public JwtAuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        var user = usersDao.getUserByEmail(loginDto.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("user not found with email: " + loginDto.getEmail());
        }

        List<String> roles = new ArrayList<>();
        var userRoles = userRoleDao.getRolesByUserUuid(user.getUuid());
        userRoles.forEach(userRole -> roles.add(userRole.getRole().toString()));

        return JwtAuthResponseDto.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .roles(roles)
                .build();
    }

    @Override
    public Users register(RegisterDto registerDto) {
        var isUserExistWithEmail = usersDao.getUserByEmail(registerDto.getEmail());

        if (isUserExistWithEmail != null) {
            throw new AlreadyFoundException("Email already exists: " + registerDto.getEmail());
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new InvalidInputException("Password not matched");
        }

        var user = Users.builder()
                .uuid(UUID.randomUUID())
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .phoneNumber(registerDto.getPhoneNumber())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        usersDao.insert(user);

        userRoleDao.insert(UserRole.builder()
                .userUuid(user.getUuid())
                .role(RoleType.USER)
                .build());

        return user;
    }
}
