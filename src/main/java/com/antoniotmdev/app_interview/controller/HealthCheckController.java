package com.antoniotmdev.app_interview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Endpoint for application health checks")
public class HealthCheckController {
    @Operation(summary = "Check application health", description = "Returns 'API is running!' if the application is running")
    @GetMapping("/health")

    public String health() {
        return "API is running!";
    }
}