package com.telerik.ridepalplaylistgenerator.controllers;

import com.telerik.ridepalplaylistgenerator.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/aboutUs")
    public String showAboutUs(){
        return"about-us";
    }
}
