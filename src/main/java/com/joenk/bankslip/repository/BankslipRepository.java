package com.joenk.bankslip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joenk.bankslip.entity.Bankslip;

@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, String> {
	
}
