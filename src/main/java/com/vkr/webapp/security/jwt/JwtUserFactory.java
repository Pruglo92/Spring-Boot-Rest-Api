package com.vkr.webapp.security.jwt;

import com.vkr.webapp.entity.Role;
import com.vkr.webapp.entity.User;
import com.vkr.webapp.entity.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getDateOfLastVisit(),
                getAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> getAuthorities(Role userRoles) {
        return Collections.singletonList(new SimpleGrantedAuthority(userRoles.getName()));
    }
}
