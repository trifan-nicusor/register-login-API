package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/user")
    public String user() {
        return "This is my user page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is my admin page";
    }
}