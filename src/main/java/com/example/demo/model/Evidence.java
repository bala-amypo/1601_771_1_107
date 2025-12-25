package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String evidenceType;

    private String fileUrl;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private DamageClaim claim;

    public Evidence() {
    }

    @PrePersist
    public void onUpload() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public DamageClaim getClaim() {
        return claim;
    }

    public void setClaim(DamageClaim claim) {
        this.claim = claim;
    }
}
