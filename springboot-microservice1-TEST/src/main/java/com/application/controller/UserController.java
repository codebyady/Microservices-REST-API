package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.application.controller.User;
import com.application.controller.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/demo")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getExample")
    public String getExample() {
        return "This is a GET API";
    }

    @PostMapping("/postExample")
    public String postExample(@RequestBody String request) {
        return "Received POST request with body: " + request;
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new user
    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created successfully!";
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User with ID " + id + " deleted successfully!";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Check if the user with the given ID exists in the database
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            return "User with ID " + id + " not found.";
        }

        // Update the user's information
        User userToUpdate = existingUser.get();
        userToUpdate.setUsername(updatedUser.getUsername());
        userToUpdate.setEmail(updatedUser.getEmail());
        userRepository.save(userToUpdate);

        return "User with ID " + id + " updated successfully!";
    }
}
