package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/claims")
@Tag(name = "Damage Claim Management")
public class DamageClaimController {

    @Autowired
    private DamageClaimService damageClaimService;

    @PostMapping("/file/{parcelId}")
    public ResponseEntity<DamageClaim> fileClaim(@PathVariable Long parcelId, @RequestBody DamageClaim claim) {
        DamageClaim savedClaim = damageClaimService.fileClaim(parcelId, claim);
        return ResponseEntity.ok(savedClaim);
    }

    @PutMapping("/evaluate/{claimId}")
    public ResponseEntity<DamageClaim> evaluateClaim(@PathVariable Long claimId) {
        DamageClaim evaluated = damageClaimService.evaluateClaim(claimId);
        return ResponseEntity.ok(evaluated);
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<DamageClaim> getClaim(@PathVariable Long claimId) {
        DamageClaim claim = damageClaimService.getClaim(claimId);
        return ResponseEntity.ok(claim);
    }
}
