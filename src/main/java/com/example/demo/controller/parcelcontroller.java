package com.example.demo.controller;

import com.example.demo.entity.Parcel;
import com.example.demo.service.ParcelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public Parcel create(@RequestBody Parcel parcel) {
        return parcelService.save(parcel);
    }

    @GetMapping
    public List<Parcel> getAll() {
        return parcelService.findAll();
    }

    @GetMapping("/{id}")
    public Parcel getById(@PathVariable Long id) {
        return parcelService.findById(id);
    }
}
