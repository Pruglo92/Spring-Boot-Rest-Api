package com.vkr.webapp.security;

import com.vkr.webapp.entity.User;
import com.vkr.webapp.security.jwt.JwtUser;
import com.vkr.webapp.security.jwt.JwtUserFactory;
import com.vkr.webapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user with username " + username + " was not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("user with username {} was loaded", username);
        return jwtUser;
    }
}
