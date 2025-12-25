package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Evidence;

public interface EvidenceService {

    Evidence uploadEvidence(Long claimId, Evidence evidence);

    List<Evidence> getEvidenceForClaim(Long claimId);
}
