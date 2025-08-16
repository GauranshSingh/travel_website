package com.travelwebsite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travelwebsite.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByFeaturedTrue();
    List<Destination> findByState(String state);
    Page<Destination> findByState(String state, Pageable pageable);
    Page<Destination> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);
    Page<Destination> findByPriceGreaterThanEqual(Integer minPrice, Pageable pageable);
    Page<Destination> findByPriceLessThanEqual(Integer maxPrice, Pageable pageable);
    List<Destination> findTop6ByOrderByCreatedAtDesc();

    // ✅ New method for search functionality
    @Query("SELECT d FROM Destination d WHERE " +
           "LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(d.location) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(d.state) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Destination> searchDestinations(@Param("query") String query);
    
}
