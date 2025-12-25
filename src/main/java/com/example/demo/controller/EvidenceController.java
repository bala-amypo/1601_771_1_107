package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;

@RestController
@RequestMapping("/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping("/upload/{claimId}")
    public ResponseEntity<Evidence> uploadEvidence(
            @PathVariable Long claimId,
            @RequestBody Evidence evidence) {

        return ResponseEntity.ok(
                evidenceService.uploadEvidence(claimId, evidence)
        );
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<List<Evidence>> getEvidence(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(
                evidenceService.getEvidenceForClaim(claimId)
        );
    }
}
