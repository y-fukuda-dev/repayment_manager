package com.example.repayment_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.repayment_manager.model.RepaymentInformation;

@Repository
public interface RepaymentInformationRepository extends JpaRepository<RepaymentInformation, Long> {
    
    @Query("SELECT MAX(r.number) FROM RepaymentInformation r")
    Long findMaxNumber();
}
