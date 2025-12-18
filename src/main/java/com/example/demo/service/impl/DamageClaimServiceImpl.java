package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private DamageClaimRepository damageClaimRepository;

    @Autowired
    private RuleEngineUtil ruleEngineUtil;

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
        claim.setParcel(parcel);
        claim.setStatus("PENDING");
        return damageClaimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        double score = ruleEngineUtil.evaluate(claim);
        claim.setScore(score);
        claim.setStatus(score >= 0.7 ? "APPROVED" : "REJECTED");
        return damageClaimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}
