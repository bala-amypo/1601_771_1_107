package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Evidence;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.EvidenceRepository;
import com.example.demo.service.EvidenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final DamageClaimRepository damageClaimRepository;

    // 🔥 REQUIRED BY TEST
    public EvidenceServiceImpl(EvidenceRepository evidenceRepository,
                               DamageClaimRepository damageClaimRepository) {
        this.evidenceRepository = evidenceRepository;
        this.damageClaimRepository = damageClaimRepository;
    }

    @Override
    public Evidence createEvidence(Evidence evidence) {
        return evidenceRepository.save(evidence);
    }

    @Override
    public Evidence getById(Long id) {
        return evidenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }

    // 🔥 REQUIRED BY TEST
    @Override
    public Evidence uploadEvidence(Long claimId, Evidence evidence) {
        DamageClaim claim = damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        evidence.setClaim(claim);
        return evidenceRepository.save(evidence);
    }

    // 🔥 REQUIRED BY TEST
    @Override
    public List<Evidence> getEvidenceForClaim(Long claimId) {
        return evidenceRepository.findByClaimId(claimId);
    }
}
