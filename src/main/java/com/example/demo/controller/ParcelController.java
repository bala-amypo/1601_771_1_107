package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/parcels")
@Tag(name = "Parcel Management")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping
    public ResponseEntity<Parcel> addParcel(@RequestBody Parcel parcel) {
        Parcel savedParcel = parcelService.addParcel(parcel);
        return ResponseEntity.ok(savedParcel);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<Parcel> getParcel(@PathVariable String trackingNumber) {
        Parcel parcel = parcelService.getByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(parcel);
    }
}
