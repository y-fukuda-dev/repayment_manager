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
import com.example.repayment_manager.service.GetRepaymentInformationService;
import com.example.repayment_manager.service.GetRepaymentInformationServiceInDto;
import com.example.repayment_manager.service.GetRepaymentInformationServiceOtDto;

@Controller
public class HomeController {

    RepaymentInformationRepository repaymentInformationRepository;

    RepaymentInformationList repeymentInformationList;

    GetRepaymentInformationService getRpyInfService;

    /**
     * コンストラクタ
     * @param repaymentInformationRepository
     * @param repaymentInformationList
     * @param getRpyInfService
     */
    @Autowired
    public HomeController(
        RepaymentInformationRepository repaymentInformationRepository, 
        RepaymentInformationList repaymentInformationList, 
        GetRepaymentInformationService getRpyInfService) {

        this.repaymentInformationRepository = repaymentInformationRepository;
        this.repeymentInformationList = repaymentInformationList;
        this.getRpyInfService = getRpyInfService;
    }

    /**
     * 「ホーム」を表示
     * @return home.html
     */
    @GetMapping("/")
    public String home() {
        return "home"; // src/main/resources/templates/home.html を返す
    }

    /**
     * 「ダッシュボード」を表示
     * @param model
     * @return dashboard.html
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        GetRepaymentInformationServiceInDto inDto = new GetRepaymentInformationServiceInDto();
        GetRepaymentInformationServiceOtDto otDto = getRpyInfService.getAllRepaymentInformation(inDto);
        model.addAttribute("informationList", otDto.getRepaymentInformation());
        return "dashboard"; // src/main/resources/templates/dashboard.html を返す
    }

    /**
     * 「新規追加」を表示
     * @return new_form.html
     */
    @GetMapping("/new")
    public String showNewForm() {
        return "new_form"; // src/main/resources/templates/new_form.html を返す
    }

    /**
     * 「設定」を表示
     * @return setting.html
     */
    @GetMapping("/setting")
    public String setting() {
        return "setting"; // src/main/resources/templates/new_form.html を返す
    }

    /**
     * 「ダッシュボード」で、レコードのステータス表示を押下した時のイベント
     * 返済ステータスを更新する。
     * @param model
     * @return dashboard.html
     */
    @GetMapping("/changeStatus") 
    public String changeStatus(Model model) {
        // 全レコードを取得
        List<RepaymentInformation> rows = repaymentInformationRepository.findAll();
        repeymentInformationList.setRepaymentInformation(rows);
        model.addAttribute("informationList", repeymentInformationList);
        
        return "dashboard"; // src/main/resources/templates/dashboard.html を返す
    }

    /**
     * 「新規追加」で、追加ボタンを押下した時のイベント
     * 返済情報テーブルに新規レコードを追加する。
     * @param information
     * @return dashboard.html
     */
    @PostMapping("/add")
    public String addInformation(
        @ModelAttribute RepaymentInformation information) {
        // 新規追加レコードの採番
        long number = 1 + repaymentInformationRepository.findMaxNumber();
        information.setNumber(number);
        information.setStatus(false);
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        information.setRegisterDt(Date.from(instant));

        repaymentInformationRepository.save(information);
        return "redirect:/dashboard";
    }

    /**
     * 共通エラー画面
     * @return error.html
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
    
    
}
