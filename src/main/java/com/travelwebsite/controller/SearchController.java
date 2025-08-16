package com.travelwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.service.DestinationService;

@Controller
public class SearchController {

    private final DestinationService destinationService;

    @Autowired
    public SearchController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destinations/search")
    public String searchDestinations(@RequestParam("query") String query, Model model) {
        List<Destination> searchResults = destinationService.searchDestinations(query);
        model.addAttribute("destinations", searchResults);
        model.addAttribute("query", query);
        return "search-results";
    }
}