package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    boolean existsByTrackingNumber(String trackingNumber);

    Optional<Parcel> findByTrackingNumber(String trackingNumber);
}
