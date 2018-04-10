package com.joenk.bankslip.component;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.joenk.bankslip.component.FineCalculate;

public class FineCalculateTest {
	
	@InjectMocks
	@Resource
	private FineCalculate fineCalculate;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void calculateFineWithoutTaxTest(){
		final LocalDate now = LocalDate.now();
		final Integer totalInCents = 1000;
		final Integer fine = fineCalculate.calculateFine(now, now, totalInCents);
		assertTrue(0 == fine);
	}
	
	@Test
	public void calculateFineTax1Test(){
		final Integer totalInCents = 1000;
		final LocalDate dateRef = LocalDate.of(2018, Month.APRIL, 9);
		final LocalDate dueDate = LocalDate.of(2018, Month.APRIL, 6);		
		final Integer fine = fineCalculate.calculateFine(dateRef, dueDate, totalInCents);
		assertTrue(5 == fine);
	}
	
	@Test
	public void calculateFineTax2Test(){
		final Integer totalInCents = 1000;
		final LocalDate dateRef = LocalDate.of(2018, Month.APRIL, 24);
		final LocalDate dueDate = LocalDate.of(2018, Month.APRIL, 4);		
		final Integer fine = fineCalculate.calculateFine(dateRef, dueDate, totalInCents);
		assertTrue(10 == fine);
	}
	
	@Test
	public void calculateIntervalDaysTest(){
		final LocalDate dateRef = LocalDate.of(2018, Month.APRIL, 12);
		final LocalDate dueDate = LocalDate.of(2018, Month.APRIL, 2);		
		final Integer intervalDays = fineCalculate.calculateIntervalDays(dateRef, dueDate);
		assertTrue(10 == intervalDays);
	}
	
	@Test
	public void getTax1Test(){
		final Integer days = 12;
		final BigDecimal tax = fineCalculate.getTax(days);
		assertTrue(tax == FineCalculate.TAX_0_01);
	}
	
	@Test
	public void getTax2Test(){
		final Integer days = 5;
		final BigDecimal tax = fineCalculate.getTax(days);
		assertTrue(tax == FineCalculate.TAX_0_005);
	}
	
	@Test
	public void calculateTaxFineTax1(){
		final BigDecimal tax = FineCalculate.TAX_0_01;
		final Integer totalInCents = 1000;
		final Integer taxFine = fineCalculate.calculateTaxFine(tax, totalInCents);
		assertTrue(10 == taxFine);
	}
	
	@Test
	public void calculateTaxFineTax2(){
		final BigDecimal tax = FineCalculate.TAX_0_005;
		final Integer totalInCents = 1000;
		final Integer taxFine = fineCalculate.calculateTaxFine(tax, totalInCents);
		assertTrue(5 == taxFine);
	}
	
}
