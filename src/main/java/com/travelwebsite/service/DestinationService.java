package com.travelwebsite.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.travelwebsite.entity.Destination;

public interface DestinationService {
    List<Destination> findAllDestinations();
    Page<Destination> findAll(Pageable pageable);
    List<Destination> findFeaturedDestinations();
    List<Destination> findDestinationsByState(String state);
    Page<Destination> findByState(String state, Pageable pageable);
    Page<Destination> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
    Page<Destination> findByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);
    Page<Destination> findByPriceLessThanEqual(Integer maxPrice, Pageable pageable);
    List<Destination> findLatestDestinations();
    Destination findDestinationById(Long id);

    // ADD THIS LINE
    List<Destination> searchDestinations(String query);
 // In DestinationService interface
    void saveDestination(Destination destination);
    void deleteDestination(Long id);
}
