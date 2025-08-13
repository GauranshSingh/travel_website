package com.travelwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.service.DestinationService;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/{id}")
    public String getDestination(@PathVariable Long id, Model model) {
        Destination destination = destinationService.findDestinationById(id);
        model.addAttribute("destination", destination);
        return "destination-detail"; // This will be the destination detail page
    }
}