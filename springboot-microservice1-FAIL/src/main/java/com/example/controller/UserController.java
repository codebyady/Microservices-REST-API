package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/demo")
@Controller
public class UserController {

    @GetMapping("/hello")
    public String getExample() {
        return "This is a GET API";
    }
    @PostMapping("/postExample")
    public String postExample(@RequestBody String request) {
        return "Received POST request with body: " + request;
    }
}