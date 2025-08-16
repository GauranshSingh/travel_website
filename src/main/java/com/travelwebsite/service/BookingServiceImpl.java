package com.travelwebsite.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.entity.User;
import com.travelwebsite.model.Booking;
import com.travelwebsite.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> findBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<Booking> findBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking createBooking(User user, Destination destination, LocalDate checkInDate, 
                               LocalDate checkOutDate, Integer numberOfGuests, String specialRequests) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setDestination(destination);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setNumberOfGuests(numberOfGuests);
        
        // Calculate total price
        long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = destination.getPrice().multiply(BigDecimal.valueOf(numberOfDays)).multiply(BigDecimal.valueOf(numberOfGuests));
        booking.setTotalPrice(totalPrice);
        
        booking.setSpecialRequests(specialRequests);
        booking.setBookingStatus(Booking.BookingStatus.PENDING);
        
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = findBookingById(id);
        booking.setBookingStatus(Booking.BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public void confirmBooking(Long id) {
        Booking booking = findBookingById(id);
        booking.setBookingStatus(Booking.BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }
}