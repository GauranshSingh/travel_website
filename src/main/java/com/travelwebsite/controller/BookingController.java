package com.travelwebsite.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.entity.User;
import com.travelwebsite.model.Booking;
import com.travelwebsite.service.BookingService;
import com.travelwebsite.service.DestinationService;
import com.travelwebsite.service.UserService;


@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final DestinationService destinationService;
    private final UserService userService;

    @Autowired
    public BookingController(BookingService bookingService, DestinationService destinationService, UserService userService) {
        this.bookingService = bookingService;
        this.destinationService = destinationService;
        this.userService = userService;
    }

    @GetMapping("/my-bookings")
    public String userBookings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userService.findUserByUsername(username);
        List<Booking> bookings = bookingService.findBookingsByUser(user);
        
        model.addAttribute("bookings", bookings);
        model.addAttribute("user", user);
        
        return "booking/my-bookings";
    }

    @GetMapping("/new/{destinationId}")
    public String newBookingForm(@PathVariable Long destinationId, Model model) {
        Destination destination = destinationService.findDestinationById(destinationId);
        model.addAttribute("destination", destination);
        model.addAttribute("booking", new Booking());
        
        return "booking/booking-form";
    }

    @PostMapping("/create")
    public String createBooking(@RequestParam Long destinationId,
                               @RequestParam LocalDate checkInDate,
                               @RequestParam LocalDate checkOutDate,
                               @RequestParam Integer numberOfGuests,
                               @RequestParam(required = false) String specialRequests,
                               RedirectAttributes redirectAttributes) {
        
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            
            User user = userService.findUserByUsername(username);
            Destination destination = destinationService.findDestinationById(destinationId);
            
            Booking booking = bookingService.createBooking(
                    user, destination, checkInDate, checkOutDate, numberOfGuests, specialRequests);
            
            redirectAttributes.addFlashAttribute("successMessage", "Booking created successfully!");
            return "redirect:/bookings/confirmation/" + booking.getId();
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating booking: " + e.getMessage());
            return "redirect:/destinations/" + destinationId;
        }
    }

    @GetMapping("/confirmation/{id}")
    public String bookingConfirmation(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        model.addAttribute("booking", booking);
        
        return "booking/confirmation";
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.cancelBooking(id);
            redirectAttributes.addFlashAttribute("successMessage", "Booking cancelled successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error cancelling booking: " + e.getMessage());
        }
        
        return "redirect:/bookings/my-bookings";
    }

    @GetMapping("/admin/all")
    public String allBookings(Model model) {
        List<Booking> bookings = bookingService.findAllBookings();
        model.addAttribute("bookings", bookings);
        
        return "admin/bookings/list";
    }

    @GetMapping("/admin/confirm/{id}")
    public String confirmBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.confirmBooking(id);
            redirectAttributes.addFlashAttribute("successMessage", "Booking confirmed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error confirming booking: " + e.getMessage());
        }
        
        return "redirect:/bookings/admin/all";
    }
}