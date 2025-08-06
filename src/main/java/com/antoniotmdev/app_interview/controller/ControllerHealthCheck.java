package com.antoniotmdev.app_interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHealthCheck {

    @GetMapping("/api/health")
    public String health() {
        return "✅ API is running!";
    }
}