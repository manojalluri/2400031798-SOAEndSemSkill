package com.example.jwt.controller;

import com.example.jwt.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Demo endpoint: replace this with real username/password authentication.
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "username is required"));
        }
        return ResponseEntity.ok(Map.of("token", jwtService.generateToken(username)));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Bearer token missing"));
        }
        String token = authorization.substring(7);
        boolean valid = jwtService.isTokenValid(token);
        if (!valid) {
            return ResponseEntity.status(401).body(Map.of("valid", false, "message", "Invalid or expired token"));
        }
        return ResponseEntity.ok(Map.of(
                "valid", true,
                "username", jwtService.extractUsername(token)
        ));
    }
}
