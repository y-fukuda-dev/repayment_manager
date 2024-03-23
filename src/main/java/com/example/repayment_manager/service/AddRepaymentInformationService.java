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

    /**
     * addRepaymentInformationメソッド
     * RepaymentInformationテーブルに新規レコードを追加する。
     * @param inDto 入力DTO
     * @return otDto 出力DTO
     */
    public AddRepaymentInformationServiceOtDto 
            addRepaymentInformation(AddRepaymentInformationServiceInDto inDto) {

        // 返済情報テーブルにレコードが存在するかチェック
        boolean flag = false;
        if (!repaymentInformationRepository.findAll().isEmpty()) {
            flag = true;
        }

        // 項番の設定
        Long number = 1L;
        // レコードがすでに存在する場合、最大値 + 1
        // レコードが存在しない場合、1とする
        if (flag) {
            number += repaymentInformationRepository.findMaxNumber();
        }

        // RepaymentInformationエンティティ
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
