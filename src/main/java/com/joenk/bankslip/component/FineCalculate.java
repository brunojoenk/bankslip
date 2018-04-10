package com.joenk.bankslip.component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class FineCalculate {
	
	public static final short DAYS = 10;
	public static final BigDecimal TAX_0_01 = new BigDecimal("0.01");
	public static final BigDecimal TAX_0_005 = new BigDecimal("0.005");
	
	public Integer calculateFine(final LocalDate dateRef, final LocalDate dueDate, final Integer totalInCents){
		final Integer intervalDays = calculateIntervalDays(dateRef, dueDate);
		if(intervalDays > 0){
			final BigDecimal tax = getTax(intervalDays);	
		    final Integer fine = calculateTaxFine(tax, totalInCents);
		    return fine;
		}
		return 0;
	}
	
	public Integer calculateIntervalDays(final LocalDate dateRef, final LocalDate dueDate){
		if(dateRef.isAfter(dueDate)){		    		    
		    final Long intervalDays = ChronoUnit.DAYS.between(dueDate, dateRef);	
		    return intervalDays.intValue();
		}
		return 0;
	}
	
	public BigDecimal getTax(final Integer days){
		if(days > DAYS){
			return TAX_0_01;
		}
		return TAX_0_005;
	}
	
	public Integer calculateTaxFine(final BigDecimal tax, final Integer totalInCents){
		final BigDecimal totalInCentsBD = new BigDecimal(totalInCents);
	    final BigDecimal totalInCentsTax = tax.multiply(totalInCentsBD);
	    return totalInCentsTax.intValue();
	}
	
}
