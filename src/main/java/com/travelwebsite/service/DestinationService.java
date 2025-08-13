package com.travelwebsite.service;

import java.util.List;

import com.travelwebsite.entity.Destination;

public interface DestinationService {
    List<Destination> findAllDestinations();
    List<Destination> findFeaturedDestinations();
    List<Destination> findDestinationsByState(String state);
    List<Destination> findLatestDestinations();
    Destination findDestinationById(Long id);
}