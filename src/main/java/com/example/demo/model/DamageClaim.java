package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DamageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimNumber;
    private String trackingNumber;
    private String damageType;
    private String description;

    private String status = "OPEN"; // OPEN / APPROVED / REJECTED

    private LocalDateTime createdAt = LocalDateTime.now();

    public DamageClaim() {}

    public DamageClaim(String claimNumber, String trackingNumber,
                       String damageType, String description) {
        this.claimNumber = claimNumber;
        this.trackingNumber = trackingNumber;
        this.damageType = damageType;
        this.description = description;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
