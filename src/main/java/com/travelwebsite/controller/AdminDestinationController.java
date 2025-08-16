package com.travelwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travelwebsite.entity.Destination;
import com.travelwebsite.service.DestinationService;

@Controller
@RequestMapping("/admin/destinations")
public class AdminDestinationController {

    private final DestinationService destinationService;

    @Autowired
    public AdminDestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    // List all destinations
    @GetMapping
    public String listDestinations(Model model) {
        model.addAttribute("destinations", destinationService.findAllDestinations());
        return "admin/destinations/list";
    }

    // Show form to create new destination
    @GetMapping("/new")
    public String createDestinationForm(Model model) {
        model.addAttribute("destination", new Destination());
        model.addAttribute("pageTitle", "Add New Destination");
        return "admin/destinations/form";
    }

    // Save new destination
    @PostMapping
    public String saveDestination(@ModelAttribute Destination destination, RedirectAttributes redirectAttributes) {
        destinationService.saveDestination(destination);
        redirectAttributes.addFlashAttribute("successMessage", "Destination saved successfully!");
        return "redirect:/admin/destinations";
    }

    // Show form to edit destination
    @GetMapping("/edit/{id}")
    public String editDestinationForm(@PathVariable Long id, Model model) {
        model.addAttribute("destination", destinationService.findDestinationById(id));
        model.addAttribute("pageTitle", "Edit Destination");
        return "admin/destinations/form";
    }

    // Update destination
    @PostMapping("/update/{id}")
    public String updateDestination(@PathVariable Long id, @ModelAttribute Destination destination, 
                                  RedirectAttributes redirectAttributes) {
        destination.setId(id);
        destinationService.saveDestination(destination);
        redirectAttributes.addFlashAttribute("successMessage", "Destination updated successfully!");
        return "redirect:/admin/destinations";
    }

    // Delete destination
    @GetMapping("/delete/{id}")
    public String deleteDestination(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        destinationService.deleteDestination(id);
        redirectAttributes.addFlashAttribute("successMessage", "Destination deleted successfully!");
        return "redirect:/admin/destinations";
    }
}
