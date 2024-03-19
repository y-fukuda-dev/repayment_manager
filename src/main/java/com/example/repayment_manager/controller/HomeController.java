package com.example.repayment_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // src/main/resources/templates/home.html を返す
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // src/main/resources/templates/dashboard.html を返す
    }
}
