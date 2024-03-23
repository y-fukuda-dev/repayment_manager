package com.example.repayment_manager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.repayment_manager.model.RepaymentInformation;

@Repository
public interface RepaymentInformationRepository extends JpaRepository<RepaymentInformation, Long> {
    
    @Query("SELECT MAX(r.number) FROM RepaymentInformation r")
    Long findMaxNumber();

    @Query("SELECT r FROM RepaymentInformation r WHERE number = :number")
    RepaymentInformation findRepaymentInformation(Long number);

    @Query("SELECT SUM(r.repaymentAmt) FROM RepaymentInformation r WHERE status = false")
    Long getRepaymentAmtTotal();

    @Transactional
    @Modifying
    @Query("UPDATE RepaymentInformation SET status = :status, repaymentDt = :repaymentDt WHERE number = :number")
    Integer changeRepaymentStatus(Long number, boolean status, Date repaymentDt);
}
