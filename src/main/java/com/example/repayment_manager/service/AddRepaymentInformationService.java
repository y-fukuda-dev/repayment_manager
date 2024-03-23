package com.example.repayment_manager.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repayment_manager.model.RepaymentInformation;
import com.example.repayment_manager.repository.RepaymentInformationRepository;

@Service
public class AddRepaymentInformationService {

    private final RepaymentInformationRepository repaymentInformationRepository;

    /**
     * コンストラクタ
     * @param repaymentInformationRepository
     */
    @Autowired
    public AddRepaymentInformationService
            (RepaymentInformationRepository repaymentInformationRepository) {
        this.repaymentInformationRepository = repaymentInformationRepository;
    }


    public AddRepaymentInformationServiceOtDto 
            addRepaymentInformation(AddRepaymentInformationServiceInDto inDto) {

        // 返済情報テーブルから項番の最大値を取得する。
        Long maxNumber = repaymentInformationRepository.findMaxNumber();
        // 採番 ← 最大値 + 1
        Long number = maxNumber + 1L;

        RepaymentInformation information = new RepaymentInformation();
        // 項番
        information.setNumber(number);
        // 返済ステータス
        information.setStatus(false);
        // 金額
        information.setRepaymentAmt(inDto.getRepaymentAmt());
        // 登録日
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        information.setRegisterDt(Date.from(instant));
        repaymentInformationRepository.save(information);
        
        // 応答編集
        AddRepaymentInformationServiceOtDto otDto = null;
        return otDto;
    } 
}
