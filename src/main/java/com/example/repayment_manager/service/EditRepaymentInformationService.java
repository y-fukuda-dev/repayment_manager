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
public class EditRepaymentInformationService {

    private final RepaymentInformationRepository repaymentInformationRepository;

    /**
     * コンストラクタ
     * @param repaymentInformationRepository
     */
    @Autowired
    public EditRepaymentInformationService
            (RepaymentInformationRepository repaymentInformationRepository) {
        this.repaymentInformationRepository = repaymentInformationRepository;
    }

    /**
     * changeRepaymentStatusメソッド
     * 返済情報テーブルで、返済ステータスを返済済みに更新し、返済日時を設定する。
     * @param inDto 入力DTO
     * @return otDto 出力DTO
     */
    public EditRepaymentInformationServiceOtDto 
            changeRepaymentStatus(EditRepaymentInformationServiceInDto inDto) {
        // 項番
        Long number = inDto.getNumber();
        
        // 項番を元に返済情報テーブルを検索
        RepaymentInformation information = 
                repaymentInformationRepository.findRepaymentInformation(number);
        
        // ステータス
        boolean statusAf = !information.getStatus();
        // 返済日時
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date repaymentDt = Date.from(instant);
        // レコードを更新
        repaymentInformationRepository.changeRepaymentStatus(number, statusAf, repaymentDt);
        
        EditRepaymentInformationServiceOtDto otDto = null;
        return otDto;

    }

}
