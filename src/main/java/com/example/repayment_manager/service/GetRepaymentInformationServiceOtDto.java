package com.example.repayment_manager.service;

import java.util.List;

import com.example.repayment_manager.model.RepaymentInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRepaymentInformationServiceOtDto {

    private Long repaymentAmtTotal;

    private List<RepaymentInformation> repaymentInformation;

}
