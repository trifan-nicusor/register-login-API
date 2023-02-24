package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    @GetMapping("/home")
    public String home() {
        return "This is my home page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is my admin page";
    }
}