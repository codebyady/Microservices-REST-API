package com.application.controller;

//import org.springframework.beans.factory.annotation.Autowired;
//import com.application.controller.User;
//import com.application.controller.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@Controller
public class UserController {

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping("/getExample")
    public String getExample() {
        return "This is a GET API";
    }

    @PostMapping("/postExample")
    public String postExample(@RequestBody String request) {
        return "Received POST request with body: " + request;
    }

//    // Create a new user
//    @PostMapping("/create")
//    public String createUser(@RequestBody User user) {
//        userRepository.save(user);
//        return "User created successfully!";
//    }
//
//    // Delete a user by ID
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userRepository.deleteById(id);
//        return "User with ID " + id + " deleted successfully!";
//    }
}
