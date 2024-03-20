package com.example.repayment_manager.model;

import lombok.Data;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "repayment_information")
public class RepaymentInformation {

    @Id
    @Column(name = "number")
    private Long number;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "repayment_amt")
    private Integer repaymentAmt;

    @Column(name = "register_dt")
    private Date registerDt;

    @Column(name = "repayment_dt")
    private Date repaymentDt;
}
