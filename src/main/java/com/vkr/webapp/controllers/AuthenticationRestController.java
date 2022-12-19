package com.vkr.webapp.controllers;

import com.vkr.webapp.dto.Credential;
import com.vkr.webapp.dto.Token;
import com.vkr.webapp.entity.User;
import com.vkr.webapp.security.jwt.JwtTokenProvider;
import com.vkr.webapp.security.jwt.JwtUser;
import com.vkr.webapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Operation(summary = "Logs user into the system")
    @PostMapping("/user/login")
    ResponseEntity<Token> loginUser(@Parameter(in = ParameterIn.DEFAULT,
            required = true,
            schema = @Schema()) @Valid @RequestBody Credential credential) {
        try {
            String username = credential.getUsername();
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, credential.getPassword()));
            var token = new Token(jwtTokenProvider.createToken(username, user.getRole()));
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username/password supplied", e);
        }
    }

    //    @Operation(summary = "Logs out current logged in user session", description = "", security = {@SecurityRequirement(name = "JwtTokenAuth"))
//    @GetMapping("/user/logout")
    ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // concern you
            String username = ((JwtUser) auth.getPrincipal()).getUsername();
            String fakePass = UUID.randomUUID().toString();
            String password = ((JwtUser) auth.getPrincipal()).getPassword();

            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, fakePass));
            jwtTokenProvider.createToken(username, user.getRole());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username/password supplied");
        }
        return ResponseEntity.ok("Log out successful!");
    }

}
