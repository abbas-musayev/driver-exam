package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.AuthRequest;
import com.example.drivingexam.security.TokenManager;
import com.example.drivingexam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private TokenManager tokenManager;

    private final UserService userService;

    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        log.info("----------Login Controller-------");

        if(!userService.approved(request.getUsername())){
            throw new RuntimeException("USER'S ACCOUNT HAS NOT BEEN CONFIRMED");
        }
        if (userService.deleted(request.getUsername())){
            throw new RuntimeException("USER'S ACCOUNT DOES NOT EXIST");
        }

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String authorities = authenticate.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            return ResponseEntity.ok(tokenManager.generateToken(request.getUsername(),authorities));
        } catch (Exception e) {
            throw e;
        }
    }
}
