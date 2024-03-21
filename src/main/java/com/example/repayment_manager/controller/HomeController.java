package com.example.repayment_manager.controller;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.repayment_manager.model.RepaymentInformation;
import com.example.repayment_manager.model.RepaymentInformationList;
import com.example.repayment_manager.repository.RepaymentInformationRepository;



@Controller
public class HomeController {

    @Autowired
    RepaymentInformationRepository repaymentInformationRepository;

    @Autowired
    RepaymentInformationList repeymentInformationList;

    @GetMapping("/")
    public String home() {
        return "home"; // src/main/resources/templates/home.html を返す
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // 全レコードを取得
        List<RepaymentInformation> rows = repaymentInformationRepository.findAll();
        repeymentInformationList.setRepaymentInformation(rows);
        model.addAttribute("informationList", repeymentInformationList);
        
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

    // 新規追加
    @PostMapping("/add")
    public String getInformation(
        @ModelAttribute RepaymentInformation information) {
        // 新規追加レコードの採番
        long number = 1 + repaymentInformationRepository.findMaxNumber();
        information.setNumber(number);
        information.setStatus(false);
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        information.setRegisterDt(Date.from(instant));

        repaymentInformationRepository.save(information);
        return "new_form";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
    
    
}
