package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;

@RestController
@RequestMapping("/claims")
public class DamageClaimController {

    private final DamageClaimService claimService;

    public DamageClaimController(DamageClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/file/{parcelId}")
    public ResponseEntity<DamageClaim> fileClaim(
            @PathVariable Long parcelId,
            @RequestBody DamageClaim claim) {

        return ResponseEntity.ok(
                claimService.fileClaim(parcelId, claim)
        );
    }

    @PutMapping("/evaluate/{claimId}")
    public ResponseEntity<DamageClaim> evaluateClaim(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(
                claimService.evaluateClaim(claimId)
        );
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<DamageClaim> getClaim(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(
                claimService.getClaim(claimId)
        );
    }
}
