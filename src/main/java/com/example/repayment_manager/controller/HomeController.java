package com.example.repayment_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.repayment_manager.model.RepaymentInformation;
import com.example.repayment_manager.repository.RepaymentInformationRepository;


@Controller
public class HomeController {

    @Autowired
    RepaymentInformationRepository repaymentInformationRepository;

    @GetMapping("/")
    public String home() {
        return "home"; // src/main/resources/templates/home.html を返す
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // src/main/resources/templates/dashboard.html を返す
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "new_form"; // src/main/resources/templates/new_form.html を返す
    }

    @GetMapping("/setting")
    public String setting() {
        return "setting"; // src/main/resources/templates/new_form.html を返す
    }

    @GetMapping("/add")
    public List<RepaymentInformation> getMethodName(@ModelAttribute RepaymentInformation repaymentInformation) {
        return repaymentInformationRepository.findAll();
    }
    
}
