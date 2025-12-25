package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;
import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository claimRepo;
    private final ParcelRepository parcelRepo;
    private final ClaimRuleRepository ruleRepo;

    public DamageClaimServiceImpl(ParcelRepository parcelRepo,
                                  DamageClaimRepository claimRepo,
                                  ClaimRuleRepository ruleRepo) {
        this.parcelRepo = parcelRepo;
        this.claimRepo = claimRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public DamageClaim createClaim(DamageClaim claim) {
        Parcel parcel = parcelRepo.findById(claim.getParcel().getId())
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
        claim.setParcel(parcel);  // Fix for setParcel()
        claim.setStatus("PENDING");
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim getByClaimNumber(String claimNumber) {
        return claimRepo.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public DamageClaim updateStatus(String claimNumber, String status) {
        DamageClaim claim = claimRepo.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    // Example evaluateClaim method for RuleEngine
    public DamageClaim evaluateClaim(Long id) {
        DamageClaim claim = claimRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        List<ClaimRule> rules = ruleRepo.findAll();
        double score = RuleEngineUtil.computeScore(claim.getClaimDescription(), rules);
        claim.setScore(score);  // Fix for setScore()
        claim.setStatus(score > 0.5 ? "APPROVED" : "REJECTED");
        return claimRepo.save(claim);
    }
}
