package com.example.demo.repository;

import com.example.demo.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {

    List<Evidence> findByClaim_Id(Long claimId);

    long countByClaim_Id(Long claimId);
}
