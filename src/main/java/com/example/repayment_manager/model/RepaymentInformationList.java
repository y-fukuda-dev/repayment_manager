package com.example.repayment_manager.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class RepaymentInformationList {

    private List<RepaymentInformation> repaymentInformation;
}
