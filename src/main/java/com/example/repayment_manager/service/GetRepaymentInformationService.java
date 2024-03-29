package com.example.repayment_manager.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.repayment_manager.model.RepaymentInformation;
import com.example.repayment_manager.repository.RepaymentInformationRepository;

@Service
public class GetRepaymentInformationService {

    private final RepaymentInformationRepository repaymentInformationRepository;

    /**
     * コンストラクタ
     * @param repaymentInformationRepository
     */
    @Autowired
    public GetRepaymentInformationService
            (RepaymentInformationRepository repaymentInformationRepository) {
        this.repaymentInformationRepository = repaymentInformationRepository;
    }

    /**
     * getAllRepaymentInformationメソッド
     * RepaymentInformationテーブルから全レコードを取得する。
     * @param inDto 入力DTO
     * @return otDto 出力DTO
     */
    public GetRepaymentInformationServiceOtDto 
            getAllRepaymentInformation(GetRepaymentInformationServiceInDto inDto) {
        
        // RepaymentInformationテーブルのレコードを全て取得
        List<RepaymentInformation> informationList = 
                repaymentInformationRepository.findAll(Sort.by(Sort.Direction.ASC, "number"));
        
        // 応答編集
        GetRepaymentInformationServiceOtDto otDto = new GetRepaymentInformationServiceOtDto();
        otDto.setRepaymentInformation(informationList);
        return otDto;
    }

    /**
     * getRepaymentAmtTotalメソッド
     * 返済情報テーブルから未返済の金額を取得する。
     * @return otDto 出力DTO
     */
    public GetRepaymentInformationServiceOtDto getRepaymentAmtTotal() {
        
        // 返済情報テーブルから未返済の返済金額を取得
        Long totalAmt = repaymentInformationRepository.getRepaymentAmtTotal();
        // nullの場合、0円をセット
        if (Objects.isNull(totalAmt)) {
            totalAmt = 0L;
        }

        // 応答編集
        GetRepaymentInformationServiceOtDto otDto = new GetRepaymentInformationServiceOtDto();
        otDto.setRepaymentAmtTotal(totalAmt);
        return otDto;
    }

}
