package com.travelwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Destination> findAll(Pageable pageable) {
        return destinationRepository.findAll(pageable);
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
    public Page<Destination> findByState(String state, Pageable pageable) {
        return destinationRepository.findByState(state, pageable);
    }

    @Override
    public Page<Destination> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable) {
        return destinationRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Destination> findByPriceGreaterThanEqual(Integer minPrice, Pageable pageable) {
        return destinationRepository.findByPriceGreaterThanEqual(minPrice, pageable);
    }

    @Override
    public Page<Destination> findByPriceLessThanEqual(Integer maxPrice, Pageable pageable) {
        return destinationRepository.findByPriceLessThanEqual(maxPrice, pageable);
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
    
    @Override
    public List<Destination> searchDestinations(String query) {
        return destinationRepository.searchDestinations(query);
    }
    

    
 // In DestinationServiceImpl
    @Override
    public void saveDestination(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

}