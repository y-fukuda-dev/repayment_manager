package com.example.repayment_manager.controller;

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
import com.example.repayment_manager.service.AddRepaymentInformationService;
import com.example.repayment_manager.service.AddRepaymentInformationServiceInDto;
import com.example.repayment_manager.service.AddRepaymentInformationServiceOtDto;
import com.example.repayment_manager.service.EditRepaymentInformationService;
import com.example.repayment_manager.service.EditRepaymentInformationServiceInDto;
import com.example.repayment_manager.service.EditRepaymentInformationServiceOtDto;
import com.example.repayment_manager.service.GetRepaymentInformationService;
import com.example.repayment_manager.service.GetRepaymentInformationServiceInDto;
import com.example.repayment_manager.service.GetRepaymentInformationServiceOtDto;

@Controller
public class HomeController {

    RepaymentInformationRepository repaymentInformationRepository;

    RepaymentInformationList repeymentInformationList;

    GetRepaymentInformationService getRpyInfService;

    AddRepaymentInformationService addRpyInfService;

    EditRepaymentInformationService editRpyInfService;

    /**
     * コンストラクタ
     * @param repaymentInformationRepository
     * @param repaymentInformationList
     * @param getRpyInfService
     * @param addRpyInfService
     */
    @Autowired
    public HomeController(
        RepaymentInformationRepository repaymentInformationRepository, 
        RepaymentInformationList repaymentInformationList, 
        GetRepaymentInformationService getRpyInfService, 
        AddRepaymentInformationService addRpyInfService, 
        EditRepaymentInformationService editRpyInfService) {

        this.repaymentInformationRepository = repaymentInformationRepository;
        this.repeymentInformationList = repaymentInformationList;
        this.getRpyInfService = getRpyInfService;
        this.addRpyInfService = addRpyInfService;
        this.editRpyInfService = editRpyInfService;
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
    public String changeStatus(
        @ModelAttribute EditRepaymentInformationServiceInDto inDto) {
        EditRepaymentInformationServiceOtDto otDto = editRpyInfService.changeRepaymentStatus(inDto);
        return "redirect:/dashboard"; // src/main/resources/templates/dashboard.html を返す
    }

    /**
     * 「新規追加」で、追加ボタンを押下した時のイベント
     * 返済情報テーブルに新規レコードを追加する。
     * @param information
     * @return dashboard.html
     */
    @PostMapping("/add")
    public String addInformation(
        @ModelAttribute AddRepaymentInformationServiceInDto inDto) {
        AddRepaymentInformationServiceOtDto otDto = addRpyInfService.addRepaymentInformation(inDto);
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
