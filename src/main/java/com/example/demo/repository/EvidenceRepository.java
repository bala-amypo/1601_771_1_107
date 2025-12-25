package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Evidence;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {

    long countByClaim_Id(Long claimId);

    List<Evidence> findByClaim_Id(Long claimId);
}
