package com.travelwebsite.repository;


import java.util.List;

import com.travelwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelwebsite.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByUserId(Long userId);
    List<Booking> findByDestinationId(Long destinationId);
    List<Booking> findByBookingStatus(Booking.BookingStatus status);
}