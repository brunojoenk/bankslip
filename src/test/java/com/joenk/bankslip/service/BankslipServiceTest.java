package com.joenk.bankslip.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joenk.bankslip.BankslipApplication;
import com.joenk.bankslip.component.BankslipValidator;
import com.joenk.bankslip.entity.Bankslip;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.exception.NotFoundEntityException;
import com.joenk.bankslip.exception.UUIDInvalidException;
import com.joenk.bankslip.model.BankslipDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BankslipApplication.class)
public class BankslipServiceTest {

	@Autowired
	public BankslipService bankslipService;
	
	@Autowired
	public BankslipValidator bankslipValidator;
	
	@Test
	public void createTest(){
		final BankslipDTO bankslipDTO = new BankslipDTO();
		bankslipDTO.setId("684e5b56-3b78-4d99-8548-f2d30c1a4b75");
		bankslipDTO.setDueDate("2018-01-01");
		bankslipDTO.setStatus("PENDING");
		bankslipDTO.setTotalInCents("100");
		bankslipDTO.setCustomer("TESTE");
		bankslipService.create(bankslipDTO);
	}
	
	@Test
	public void listAllTest() {
		final BankslipDTO bankslipDTO = new BankslipDTO();
		final String id = "771fa05e-2884-4a3c-b4ed-536689e61b46";
		bankslipDTO.setId(id);
		bankslipDTO.setDueDate("2018-01-01");
		bankslipDTO.setStatus("PENDING");
		bankslipDTO.setTotalInCents("100");
		bankslipDTO.setCustomer("TESTE");
		
		final List<BankslipDTO> listBankslipDTOMock = new ArrayList<BankslipDTO>();
		listBankslipDTOMock.add(bankslipDTO);
		
		final BankslipService bankslipService = Mockito.mock(BankslipService.class);
	    when(bankslipService.listAll()).thenReturn(listBankslipDTOMock);
	    
	    final List<BankslipDTO> listBankslipDTO = bankslipService.listAll();
		assertTrue(listBankslipDTOMock.equals(listBankslipDTO));
	}
	
	@Test
	public void payTest() {
		final String id = "771fa05e-2884-4a3c-b4ed-536689e61b46";
		final Bankslip bankslip = new Bankslip();
		bankslip.setId(id);
		bankslip.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslip.setStatus(Status.PAID);
		bankslip.setTotalInCents(100);
		bankslip.setCustomer("TESTE");
		final BankslipService bankslipService = Mockito.mock(BankslipService.class);
	    when(bankslipService.pay(id)).thenReturn(bankslip);
	    final Bankslip mock = bankslipService.pay(id);
	    assertTrue(bankslip.equals(mock));
	}

	@Test
	public void cancelTest() {
		final String id = "771fa05e-2884-4a3c-b4ed-536689e61b46";
		final Bankslip bankslip = new Bankslip();
		bankslip.setId(id);
		bankslip.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslip.setStatus(Status.CANCELED);
		bankslip.setTotalInCents(100);
		bankslip.setCustomer("TESTE");
		final BankslipService bankslipService = Mockito.mock(BankslipService.class);
	    when(bankslipService.cancel(id)).thenReturn(bankslip);
	    final Bankslip mock = bankslipService.cancel(id);
		assertTrue(bankslip.equals(mock));
	}
	
	@Test
	public void getByIdTest(){
		final BankslipDTO bankslipDTO = new BankslipDTO();
		final String id = "771fa05e-2884-4a3c-b4ed-536689e61b46";
		bankslipDTO.setId(id);
		bankslipDTO.setDueDate("2018-01-01");
		bankslipDTO.setStatus("PENDING");
		bankslipDTO.setTotalInCents("100");
		bankslipDTO.setCustomer("TESTE");
		final BankslipService bankslipService = Mockito.mock(BankslipService.class);
	    when(bankslipService.getById(id)).thenReturn(bankslipDTO);
		final BankslipDTO test = bankslipService.getById(id);
		assertTrue(bankslipDTO.equals(test));
	}
	
	@Test(expected = NotFoundEntityException.class)
	public void getByIdTestNotFound(){
		bankslipService.getById("771fa05e-2884-4a3c-b4ed-536689e61b46");
	}
	
	@Test(expected = UUIDInvalidException.class)
	public void getByIdTestUUIDInvalid(){
		bankslipService.getById("154");
	}
	
	@Test
	public void getEntityByIdTest(){
		final String id = "771fa05e-2884-4a3c-b4ed-536689e61b46";
		final Bankslip bankslip = new Bankslip();
		bankslip.setId(id);
		bankslip.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslip.setStatus(Status.PENDING);
		bankslip.setTotalInCents(100);
		bankslip.setCustomer("TESTE");
		final BankslipService bankslipService = Mockito.mock(BankslipService.class);
	    when(bankslipService.getEntityById(id)).thenReturn(bankslip);
	    final Bankslip bankslipMock = bankslipService.getEntityById(id);
	    assertTrue(bankslip.equals(bankslipMock));
	}
	
	@Test(expected = NotFoundEntityException.class)
	public void getEntityByIdInvalid(){
		bankslipService.getEntityById("85456");
	}
	
}
