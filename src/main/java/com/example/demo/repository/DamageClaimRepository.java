package com.example.demo.repository;

import com.example.demo.model.DamageClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DamageClaimRepository extends JpaRepository<DamageClaim, Long> {

    Optional<DamageClaim> findByClaimNumber(String claimNumber);

    // 🔥 REQUIRED BY MasterTestNGSuiteTest
    List<DamageClaim> findByParcel_Id(Long parcelId);
}
