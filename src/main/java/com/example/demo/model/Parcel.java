package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Parcel {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String trackingNumber;

    private String senderName;
    private String receiverName;
    private Double weightKg;
    private LocalDateTime deliveredAt;
    public Parcel(){}
    public Parcel(String trackingNumber, String senderName, String receiverName, Double weightKg,
            LocalDateTime deliveredAt) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weightKg = weightKg;
        this.deliveredAt = deliveredAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }
    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
    public Long getId() {
        return id;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public String getSenderName() {
        return senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public Double getWeightKg() {
        return weightKg;
    }
    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }
    
}
