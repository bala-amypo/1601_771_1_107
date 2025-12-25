package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.model.ClaimRule;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.service.DamageClaimService;
import com.example.demo.util.RuleEngineUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final ParcelRepository parcelRepo;
    private final DamageClaimRepository claimRepo;
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
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim getByClaimNumber(String claimNumber) {
        return claimRepo.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public DamageClaim updateStatus(String claimNumber, String status) {
        DamageClaim claim = getByClaimNumber(claimNumber);
        claim.setStatus(status);
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepo.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
        claim.setParcel(parcel);
        claim.setStatus("PENDING");
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = claimRepo.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        List<ClaimRule> rules = ruleRepo.findAll();
        double score = RuleEngineUtil.computeScore(claim.getClaimDescription(), rules);
        claim.setScore(score);

        if (score > 0.5) {
            claim.setStatus("APPROVED");
        } else {
            claim.setStatus("REJECTED");
        }

        return claimRepo.save(claim);
    }
}
