package com.travelwebsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelwebsite.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByFeaturedTrue();
    List<Destination> findByState(String state);
    List<Destination> findTop6ByOrderByCreatedAtDesc();
}