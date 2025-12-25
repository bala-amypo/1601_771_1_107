package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public ResponseEntity<Parcel> addParcel(@RequestBody Parcel parcel) {
        return ResponseEntity.ok(parcelService.addParcel(parcel));
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<Parcel> getParcel(
            @PathVariable String trackingNumber) {

        return ResponseEntity.ok(
                parcelService.getByTrackingNumber(trackingNumber)
        );
    }
}
