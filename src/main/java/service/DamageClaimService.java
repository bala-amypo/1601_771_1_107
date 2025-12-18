package com.example.demo.service;

import com.example.demo.entity.DamageClaim;

public interface DamageClaimService {
    DamageClaim fileClaim(Long parcelId, DamageClaim claim);
    DamageClaim evaluateClaim(Long claimId);
    DamageClaim getClaim(Long claimId);
}
