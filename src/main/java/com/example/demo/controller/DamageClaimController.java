package com.example.demo.controller;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/damage-claims")
public class DamageClaimController {

    private final DamageClaimService service;

    public DamageClaimController(DamageClaimService service) {
        this.service = service;
    }

    @PostMapping
    public DamageClaim create(@RequestBody DamageClaim claim) {
        return service.createClaim(claim);
    }

    @GetMapping("/{claimNumber}")
    public DamageClaim get(@PathVariable String claimNumber) {
        return service.getByClaimNumber(claimNumber);
    }

    @PutMapping("/{claimNumber}/status/{status}")
    public DamageClaim updateStatus(@PathVariable String claimNumber,
                                    @PathVariable String status) {
        return service.updateStatus(claimNumber, status);
    }
}
