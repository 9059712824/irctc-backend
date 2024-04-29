package com.learning.irctcbackend.security;

import com.learning.irctcbackend.dao.UserRoleDao;
import com.learning.irctcbackend.dao.UsersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersDao usersDao;
    private final UserRoleDao userRoleDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = usersDao.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        var userRoles = userRoleDao.getRolesByUserUuid(user.getUuid());

        Set<GrantedAuthority> authorities = userRoles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.getRole().toString())).collect(Collectors.toSet());
        System.out.println(authorities);

        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                authorities
        );
    }

}
