package com.example.demo.service;

import com.example.demo.entity.Parcel;
import com.example.demo.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Parcel save(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public List<Parcel> findAll() {
        return parcelRepository.findAll();
    }

    public Parcel findById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }
}
