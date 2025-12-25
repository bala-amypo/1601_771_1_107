package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.service.DamageClaimService;

public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository repo;

    public DamageClaimServiceImpl(DamageClaimRepository repo) {
        this.repo = repo;
    }

    @Override
    public DamageClaim createClaim(DamageClaim claim) {
        if (repo.existsByClaimNumber(claim.getClaimNumber())) {
            throw new RuntimeException("Claim already exists");
        }
        return repo.save(claim);
    }

    @Override
    public DamageClaim getByClaimNumber(String claimNumber) {
        return repo.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public DamageClaim updateStatus(String claimNumber, String status) {
        DamageClaim claim = getByClaimNumber(claimNumber);
        claim.setStatus(status);
        return repo.save(claim);
    }
}
