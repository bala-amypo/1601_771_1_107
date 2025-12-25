package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "damage_claims")
public class DamageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimDescription;

    private LocalDateTime filedAt;

    private String status;

    private Double score;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private Set<Evidence> evidenceList = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "claim_applied_rules",
        joinColumns = @JoinColumn(name = "claim_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<ClaimRule> appliedRules = new HashSet<>();

    public DamageClaim() {
        this.status = "PENDING";
    }

    @PrePersist
    public void onCreate() {
        this.filedAt = LocalDateTime.now();
    }

    // getters and setters

    public Long getId() { return id; }

    public String getClaimDescription() { return claimDescription; }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public LocalDateTime getFiledAt() { return filedAt; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Double getScore() { return score; }

    public void setScore(Double score) { this.score = score; }

    public Parcel getParcel() { return parcel; }

    public void setParcel(Parcel parcel) { this.parcel = parcel; }

    public Set<ClaimRule> getAppliedRules() { return appliedRules; }

    public void setAppliedRules(Set<ClaimRule> appliedRules) {
        this.appliedRules = appliedRules;
    }
}
