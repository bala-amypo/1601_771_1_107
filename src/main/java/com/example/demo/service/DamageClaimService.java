package com.example.demo.service;

import com.example.demo.model.DamageClaim;

public interface DamageClaimService {

    DamageClaim createClaim(DamageClaim claim);

    DamageClaim getByClaimNumber(String claimNumber);

    DamageClaim updateStatus(String claimNumber, String status);
}
