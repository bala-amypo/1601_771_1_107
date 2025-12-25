package com.example.demo.service.impl;

import com.example.demo.model.Evidence;
import com.example.demo.repository.EvidenceRepository;
import com.example.demo.service.EvidenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    private final EvidenceRepository repository;

    public EvidenceServiceImpl(EvidenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evidence createEvidence(Evidence evidence) {
        return repository.save(evidence);
    }

    @Override
    public Evidence getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Evidence> getAllEvidence() {
        return repository.findAll();
    }
}
