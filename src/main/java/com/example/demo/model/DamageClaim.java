package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DamageClaim {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Parcel parcel;

    private String claimDescription;
    private LocalDateTime filedAt;
    private String status = "PENDING";
    private Double score;

    @ManyToMany
    private List<ClaimRule> appliedRules;

    @PrePersist
    public void onCreate() {
        filedAt = LocalDateTime.now();
    }
    public DamageClaim(){}
    public DamageClaim(Parcel parcel, String claimDescription, LocalDateTime filedAt, String status, Double score,
            List<ClaimRule> appliedRules) {
        this.parcel = parcel;
        this.claimDescription = claimDescription;
        this.filedAt = filedAt;
        this.status = status;
        this.score = score;
        this.appliedRules = appliedRules;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }
    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }
    public void setFiledAt(LocalDateTime filedAt) {
        this.filedAt = filedAt;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public void setAppliedRules(List<ClaimRule> appliedRules) {
        this.appliedRules = appliedRules;
    }
    public Long getId() {
        return id;
    }
    public Parcel getParcel() {
        return parcel;
    }
    public String getClaimDescription() {
        return claimDescription;
    }
    public LocalDateTime getFiledAt() {
        return filedAt;
    }
    public String getStatus() {
        return status;
    }
    public Double getScore() {
        return score;
    }
    public List<ClaimRule> getAppliedRules() {
        return appliedRules;
    }

    
}
