package com.joenk.bankslip.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.joenk.bankslip.BankslipApplication;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.model.BankslipDTO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=BankslipApplication.class)
public class BankslipControllerTest {
	
	@Autowired
	private BankslipController bankslipController;
	
	private BankslipDTO createBankslipDTO = null;
	
	@Before
	public void setUp(){
		createBankslipDTO = new BankslipDTO();
		createBankslipDTO.setDueDate("2018-01-01");
		createBankslipDTO.setCustomer("TESTE");
		createBankslipDTO.setTotalInCents(String.valueOf(1000));
		createBankslipDTO.setStatus(Status.PENDING.name());
		
		bankslipController.create(createBankslipDTO);		
	}
	
	@Test(expected = HttpMessageNotReadableException.class)
	public void createException(){
		bankslipController.create(null);
	}
	
	@Test
	public void listAllTest(){		
		ResponseEntity<Object> response = bankslipController.listAll();				
		assertTrue(HttpStatus.OK.value() == response.getStatusCodeValue());
	}
	
	@Test
	public void getByIdTest(){		
		ResponseEntity<Object> response = bankslipController.getById(createBankslipDTO.getId());				
		assertTrue(HttpStatus.OK.value() == response.getStatusCodeValue());
	}
	
	@Test
	public void payTest(){		
		ResponseEntity<Object> response = bankslipController.pay(createBankslipDTO.getId());				
		assertTrue(HttpStatus.NO_CONTENT.value() == response.getStatusCodeValue());
	}
	
	@Test
	public void cancelTest(){		
		ResponseEntity<Object> response = bankslipController.cancel(createBankslipDTO.getId());				
		assertTrue(HttpStatus.NO_CONTENT.value() == response.getStatusCodeValue());
	}
	
}
