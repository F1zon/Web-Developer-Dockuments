package com.example.webdev.controllers;

import com.example.webdev.config.JWTAuthResponse;
import com.example.webdev.db.dto.LoginDto;
import com.example.webdev.service.AuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto) {

        log.info("Authenticating user");
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}
