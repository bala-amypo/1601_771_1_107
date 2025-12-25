package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DamageClaim;

public interface DamageClaimRepository extends JpaRepository<DamageClaim, Long> {

    List<DamageClaim> findByParcel_Id(Long parcelId);

    Optional<DamageClaim> findById(Long claimId);
}
