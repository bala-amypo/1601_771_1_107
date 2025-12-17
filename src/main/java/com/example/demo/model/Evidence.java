package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Evidence {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private DamageClaim claim;

    private String evidenceType;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    @PrePersist
    public void onUpload() {
        uploadedAt = LocalDateTime.now();
    }
    public Evidence(){}
    public Evidence(DamageClaim claim, String evidenceType, String fileUrl, LocalDateTime uploadedAt) {
        this.claim = claim;
        this.evidenceType = evidenceType;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setClaim(DamageClaim claim) {
        this.claim = claim;
    }
    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    public Long getId() {
        return id;
    }
    public DamageClaim getClaim() {
        return claim;
    }
    public String getEvidenceType() {
        return evidenceType;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
}
