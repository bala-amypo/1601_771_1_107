package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {

    @Autowired
    private EvidenceService evidenceService;
    @PostMapping("/upload/{claimId}")
    public ResponseEntity<Evidence> uploadEvidence(
            @PathVariable Long claimId,
            @RequestBody Evidence evidence) {
        Evidence savedEvidence = evidenceService.uploadEvidence(claimId, evidence);
        return ResponseEntity.ok(savedEvidence);
    }
    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<Evidence>> getEvidenceForClaim(@PathVariable Long claimId) {
        List<Evidence> evidenceList = evidenceService.getEvidenceForClaim(claimId);
        return ResponseEntity.ok(evidenceList);
    }
}
