package com.example.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {
    @GetMapping("/api/protected")
    public Map<String, String> protectedEndpoint() {
        return Map.of("message", "JWT validation successful. You accessed a protected API.");
    }
}
