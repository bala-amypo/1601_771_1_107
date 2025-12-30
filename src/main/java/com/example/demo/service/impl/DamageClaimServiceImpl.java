package com.example.demo.service.impl;

import com.example.demo.model.ClaimRule;
import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.DamageClaimService;
import org.springframework.stereotype.Service;
import com.example.demo.util.RuleEngineUtil;

import java.util.List;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final ParcelRepository parcelRepository;
    private final DamageClaimRepository damageClaimRepository;
    private final ClaimRuleRepository claimRuleRepository;


    public DamageClaimServiceImpl(
            ParcelRepository parcelRepository,
            DamageClaimRepository damageClaimRepository,
            ClaimRuleRepository claimRuleRepository
    ) {
        this.parcelRepository = parcelRepository;
        this.damageClaimRepository = damageClaimRepository;
        this.claimRuleRepository = claimRuleRepository;
    }

    @Override
    public DamageClaim createClaim(DamageClaim claim) {
        return damageClaimRepository.save(claim);
    }

    @Override
    public DamageClaim getByClaimNumber(String claimNumber) {
        return damageClaimRepository.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public DamageClaim updateStatus(String claimNumber, String status) {
        DamageClaim claim = damageClaimRepository.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(status);
        return damageClaimRepository.save(claim);
    }

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

    List<ClaimRule> rules = claimRuleRepository.findAll();

    double score = RuleEngineUtil.computeScore(claim.getClaimDescription(), rules);


    if (rules != null && !rules.isEmpty()) {
        claim.getAppliedRules().addAll(rules);
    }


    if (score > 0) {
        claim.setStatus("APPROVED");
        claim.setScore(score);
    } else {
        claim.setStatus("REJECTED");
        claim.setScore(0.0);  
    }

    return damageClaimRepository.save(claim);
}



    @Override
    public DamageClaim getClaim(Long claimId) {
        return damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}
