package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final AdminRepository adminRepository; // Assuming you have an AdminRepository

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/getExample")
    public String getExample() {
        return "This is a GET API for admin";
    }

    // Create a new admin
    @PostMapping("/create")
    public String createAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
        return "Admin created successfully!";
    }

    @GetMapping("/getAdmins/{id}")
    public ResponseEntity<Admin> getUserById(@PathVariable Long id) {
        Optional<Admin> userOptional = adminRepository.findById(id);

        if (userOptional.isPresent()) {
            Admin admin = userOptional.get();
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an admin by ID
    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminRepository.deleteById(id);
        return "Admin with ID " + id + " deleted successfully!";
    }

    @PutMapping("/update/{id}")
    public String updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        // Check if the admin with the given ID exists in the database
        Optional<Admin> existingAdmin = adminRepository.findById(id);
        if (!existingAdmin.isPresent()) {
            return "Admin with ID " + id + " not found.";
        }

        // Update the admin's information
        Admin adminToUpdate = existingAdmin.get();
        adminToUpdate.setUsername(updatedAdmin.getUsername());
        adminToUpdate.setEmail(updatedAdmin.getEmail());
        adminRepository.save(adminToUpdate);

        return "Admin with ID " + id + " updated successfully!";
    }
}
