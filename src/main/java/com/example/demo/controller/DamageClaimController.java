package com.example.demo.controller;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping("/claims")
@SecurityRequirement(name="bearerAuth")

public class DamageClaimController {

    private final DamageClaimService service;

    public DamageClaimController(DamageClaimService service) {
        this.service = service;
    }

    @PostMapping
    public DamageClaim createClaim(@RequestBody DamageClaim claim) {
        return service.createClaim(claim);
    }

    @GetMapping("/{claimNumber}")
    public DamageClaim getByClaimNumber(@PathVariable String claimNumber) {
        return service.getByClaimNumber(claimNumber);
    }

    @PutMapping("/{claimNumber}/status")
    public DamageClaim updateStatus(@PathVariable String claimNumber,
                                    @RequestParam String status) {
        return service.updateStatus(claimNumber, status);
    }
}
