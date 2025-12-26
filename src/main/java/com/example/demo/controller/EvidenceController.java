package com.example.demo.controller;

import com.example.demo.model.Evidence;
import com.example.demo.service.EvidenceService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/evidence")
@SecurityRequirement(name="bearerAuth")

public class EvidenceController {

    private final EvidenceService service;

    public EvidenceController(EvidenceService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Evidence createEvidence(@RequestBody Evidence evidence) {
        return service.createEvidence(evidence);
    }

    @GetMapping("/{id}")
    public Evidence getEvidence(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Evidence> getAllEvidence() {
        return service.getAllEvidence();
    }
}
