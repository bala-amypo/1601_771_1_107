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
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepo.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found with id " + parcelId));

        claim.setParcel(parcel);
        claim.setStatus("PENDING");
        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = claimRepo.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id " + claimId));

        List<ClaimRule> rules = ruleRepo.findAll();
        double score = RuleEngineUtil.computeScore(claim.getClaimDescription(), rules);
        claim.setScore(score);

        claim.setStatus(score > 0.5 ? "APPROVED" : "REJECTED");

        return claimRepo.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepo.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id " + claimId));
    }

    @Override
    public List<DamageClaim> getClaimsByParcelId(Long parcelId) {
        return claimRepo.findByParcel_Id(parcelId);
    }
}
