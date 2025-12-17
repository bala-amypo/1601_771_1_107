package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DamageClaim;
import com.example.demo.entity.Parcel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClaimRuleRepository;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.util.RuleEngineUtil;

@Service
public class DamageClaimServiceImpl {
    private final ParcelRepository parcelRepo;
    private final DamageClaimRepository claimRepo;
    private final ClaimRuleRepository ruleRepo;

    public DamageClaimServiceImpl(ParcelRepository p, DamageClaimRepository c, ClaimRuleRepository r) {
        this.parcelRepo = p;
        this.claimRepo = c;
        this.ruleRepo = r;
    }

    public DamageClaim fileClaim(Long parcelid, DamageClaim claim) {
        Parcel p = parcelRepo.findById(parcelid)
            .orElseThrow(() -> new ResourceNotFoundException("parcel not"));
        claim.setParcel(p);
        return claimRepo.save(claim);
    }

    public DamageClaim evaluateClaim(Long claimid) {
        DamageClaim claim = claimRepo.findById(claimid)
            .orElseThrow(() -> new ResourceNotFoundException("claim"));
        double score = RuleEngineUtil.computeScore(
            claim.getClaimDescription(), ruleRepo.findAll());
        claim.setScore(score);
        claim.setStatus(score > 50 ? "APPROVED" : "REJECTED");
        return claimRepo.save(claim);
    }
}
