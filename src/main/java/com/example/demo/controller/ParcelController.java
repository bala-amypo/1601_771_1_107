package com.example.demo.controller;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public Parcel addParcel(@RequestBody Parcel parcel) {
        return service.addParcel(parcel);
    }

    @GetMapping("/{trackingNumber}")
    public Parcel getParcel(@PathVariable String trackingNumber) {
        return service.getByTrackingNumber(trackingNumber);
    }
}
