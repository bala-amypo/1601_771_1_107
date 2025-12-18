package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Evidence;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.EvidenceRepository;
import com.example.demo.service.EvidenceService;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    @Autowired
    private DamageClaimRepository damageClaimRepository;

    @Autowired
    private EvidenceRepository evidenceRepository;

    @Override
    public Evidence uploadEvidence(Long claimId, Evidence evidence) {
        DamageClaim claim = damageClaimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        evidence.setClaim(claim);
        return evidenceRepository.save(evidence);
    }

    @Override
    public List<Evidence> getEvidenceForClaim(Long claimId) {
        return evidenceRepository.findByClaimId(claimId);
    }
}
