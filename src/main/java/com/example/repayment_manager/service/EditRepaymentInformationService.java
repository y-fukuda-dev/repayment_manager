package com.example.repayment_manager.service;

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

    public EditRepaymentInformationServiceOtDto 
            changeRepaymentStatus(EditRepaymentInformationServiceInDto inDto) {
        // 項番
        Long number = inDto.getNumber();
        
        // 項番を元に返済情報テーブルを検索
        RepaymentInformation information = 
                repaymentInformationRepository.findRepaymentInformation(number);
        
        // ステータスを更新
        boolean statusAf = !information.getStatus();
        repaymentInformationRepository.changeRepaymentStatus(number, statusAf);
        
        EditRepaymentInformationServiceOtDto otDto = null;
        return otDto;

    }

}
