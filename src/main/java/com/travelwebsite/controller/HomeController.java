package com.travelwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.service.DestinationService;

@Controller
public class HomeController {

    private final DestinationService destinationService;

    @Autowired
    public HomeController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Get featured destinations for the homepage
        List<Destination> featuredDestinations = destinationService.findFeaturedDestinations();
        model.addAttribute("featuredDestinations", featuredDestinations);
        
        // Get latest destinations for the carousel
        List<Destination> latestDestinations = destinationService.findLatestDestinations();
        model.addAttribute("latestDestinations", latestDestinations);
        
        return "index"; // This will be the homepage Thymeleaf template
    }
    
    @GetMapping("/destinations")
    public String listAllDestinations(Model model) {
        List<Destination> allDestinations = destinationService.findAllDestinations();
        model.addAttribute("destinations", allDestinations);
        return "destinations-list";
    }
}