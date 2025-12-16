package com.example.demo.model;

import java.time.LocalDateTime;

public class Parcel {
    private Long id;
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    private LocalDateTime deliveredAt;
    private Double weightKg;

public Parcel(){

}

public Parcel(String trackingNumber, String senderName, String receiverName, LocalDateTime deliveredAt,
        Double weightKg) {
    this.trackingNumber = trackingNumber;
    this.senderName = senderName;
    this.receiverName = receiverName;
    this.deliveredAt = deliveredAt;
    this.weightKg = weightKg;
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

public void setDeliveredAt(LocalDateTime deliveredAt) {
    this.deliveredAt = deliveredAt;
}

public void setWeightKg(Double weightKg) {
    this.weightKg = weightKg;
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

public LocalDateTime getDeliveredAt() {
    return deliveredAt;
}

public Double getWeightKg() {
    return weightKg;
}

}
