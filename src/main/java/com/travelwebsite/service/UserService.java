package com.travelwebsite.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService; // <-- Add this import

import com.travelwebsite.entity.User;

// By extending UserDetailsService, we're making it part of our service contract.
public interface UserService extends UserDetailsService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User findUserByUsername(String username);
    User saveUser(User user);
    void deleteUser(Long id);
}