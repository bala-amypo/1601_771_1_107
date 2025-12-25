package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.service.DamageClaimService;
import org.springframework.stereotype.Service;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository repository;

    public DamageClaimServiceImpl(DamageClaimRepository repository) {
        this.repository = repository;
    }

    @Override
    public DamageClaim createClaim(DamageClaim claim) {
        return repository.save(claim);
    }

    @Override
    public DamageClaim getByClaimNumber(String claimNumber) {
        return null; // implement later if needed
    }

    @Override
    public DamageClaim updateStatus(String claimNumber, String status) {
        return null;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        return repository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        return repository.findById(claimId).orElse(null);
    }

    // 🔥 REQUIRED BY TEST
    @Override
    public DamageClaim getClaim(Long claimId) {
        return repository.findById(claimId).orElse(null);
    }
}
