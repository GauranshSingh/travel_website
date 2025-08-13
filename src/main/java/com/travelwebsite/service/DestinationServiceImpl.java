package com.travelwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.repository.DestinationRepository;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<Destination> findAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public List<Destination> findFeaturedDestinations() {
        return destinationRepository.findByFeaturedTrue();
    }

    @Override
    public List<Destination> findDestinationsByState(String state) {
        return destinationRepository.findByState(state);
    }

    @Override
    public List<Destination> findLatestDestinations() {
        return destinationRepository.findTop6ByOrderByCreatedAtDesc();
    }

    @Override
    public Destination findDestinationById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }
}