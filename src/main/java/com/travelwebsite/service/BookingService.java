package com.travelwebsite.service;


import java.time.LocalDate;
import java.util.List;

import com.travelwebsite.entity.User;
import com.travelwebsite.entity.Destination;
import com.travelwebsite.model.Booking;

public interface BookingService {
    List<Booking> findAllBookings();
    Booking findBookingById(Long id);
    List<Booking> findBookingsByUser(User user);
    List<Booking> findBookingsByUserId(Long userId);
    Booking saveBooking(Booking booking);
    void deleteBooking(Long id);
    Booking createBooking(User user, Destination destination, LocalDate checkInDate, 
                         LocalDate checkOutDate, Integer numberOfGuests, String specialRequests);
    void cancelBooking(Long id);
    void confirmBooking(Long id);
}