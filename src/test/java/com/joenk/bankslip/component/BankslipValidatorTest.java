package com.joenk.bankslip.component;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.joenk.bankslip.component.BankslipValidator;
import com.joenk.bankslip.entity.Bankslip;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.exception.FieldInvalidValueException;
import com.joenk.bankslip.exception.NotFoundEntityException;
import com.joenk.bankslip.exception.UUIDInvalidException;
import com.joenk.bankslip.model.BankslipDTO;

public class BankslipValidatorTest {
	
	@InjectMocks
	@Resource
	private BankslipValidator bankslipValidator;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void converDTOtoEntityTest(){
		final BankslipDTO bankslipDTO = new BankslipDTO();
		final String uuid = UUID.randomUUID().toString();
		bankslipDTO.setId(uuid);
		bankslipDTO.setDueDate("2018-01-01");
		bankslipDTO.setStatus("PENDING");
		bankslipDTO.setTotalInCents("100");
		bankslipDTO.setCustomer("TESTE");
		
		final Bankslip bankslip = bankslipValidator.converDTOtoEntity(bankslipDTO);
		
		final Bankslip bankslipToTest = new Bankslip();
		bankslipToTest.setId(uuid);
		bankslipToTest.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslipToTest.setStatus(Status.PENDING);
		bankslipToTest.setTotalInCents(100);
		bankslipToTest.setCustomer("TESTE");

		assertTrue(bankslip.equals(bankslipToTest));
	}
	
	@Test
	public void convertEntityToDtoTest(){
		final Bankslip bankslip = new Bankslip();
		final String uuid = UUID.randomUUID().toString();
		bankslip.setId(uuid);
		bankslip.setDueDate(bankslipValidator.getDueDate("2018-01-01", null));
		bankslip.setStatus(Status.PENDING);
		bankslip.setTotalInCents(100);
		bankslip.setCustomer("TESTE");
		
		final BankslipDTO bankslipDTO = bankslipValidator.convertEntityToDto(bankslip);
		
		final BankslipDTO bankslipDTOToTest = new BankslipDTO();
		bankslipDTOToTest.setId(uuid);
		bankslipDTOToTest.setDueDate("2018-01-01");
		bankslipDTOToTest.setStatus("PENDING");
		bankslipDTOToTest.setTotalInCents("100");
		bankslipDTOToTest.setCustomer("TESTE");

		assertTrue(bankslipDTOToTest.equals(bankslipDTO));
		
	}
	
	@Test(expected = FieldInvalidValueException.class)
	public void validTextMessageErrorTestException(){
		final StringBuilder sbMessage = new StringBuilder();
		sbMessage.append("The field due_date is a required field");
		bankslipValidator.validTextMessageError(sbMessage);
	}
	
	@Test
	public void validTextMessageErrorTestOk(){
		final StringBuilder sbMessage = new StringBuilder();
		final Boolean isValid = bankslipValidator.validTextMessageError(sbMessage);
		assertTrue(isValid);
	}
	
	@Test(expected = FieldInvalidValueException.class)
	public void validTextFieldTestException(){		
		bankslipValidator.validTextField("  ", "due_date");
	}
	
	@Test
	public void validTextFieldTextOk(){
		final Boolean isValid = bankslipValidator.validTextField("2018-01-01", "due_date");
		assertTrue(isValid);
	}
	
	@Test
	public void getDueDateTestOk(){
		final String dateStr = "2018-01-01";
		final Date date = bankslipValidator.getDueDate(dateStr, null);
		final String dateToString = bankslipValidator.getTextDueDate(date);
		assertTrue(dateStr.equals(dateToString));
	}
	
	@Test
	public void getDueDateTestInvalid(){
		final StringBuilder sbMessage = new StringBuilder();
		bankslipValidator.getDueDate("01-01-2018", sbMessage);
		assertTrue(sbMessage.toString().contains("Field due_date must be pattern YYYY-MM-DD"));
	}
	
	@Test
	public void getStatusTestOk(){
		final Status status = bankslipValidator.getStatus("PENDING", null);
		assertTrue(Status.PENDING.equals(status));
	}
		
	@Test
	public void getStatusTestInvalid(){
		final StringBuilder sbMessage = new StringBuilder();
		bankslipValidator.getStatus("INVALID", sbMessage);
		assertTrue(sbMessage.toString().contains("Field status must be PENDING, PAID or CANCELED"));
	}
	
	@Test
	public void getTotalInCentsTestOk(){
		final Integer test = 1000;
		final Integer totalInCents = bankslipValidator.getTotalInCents("1000", null);
		assertTrue(test.equals(totalInCents));
	}
	
	@Test
	public void getTotalInCentsTestInvalidCaracter(){
		final StringBuilder sbMessage = new StringBuilder();
		bankslipValidator.getTotalInCents("A", sbMessage);
		assertTrue(sbMessage.toString().contains("Only number is valid"));
	}
	
	@Test
	public void getTotalInCentsTestInvalidNumber(){
		final StringBuilder sbMessage = new StringBuilder();
		bankslipValidator.getTotalInCents("0", sbMessage);
		assertTrue(sbMessage.toString().contains("The number must be greater than 0"));
	}
	
	@Test
	public void getCustomerTestOk(){
		final String str = "TESTE";
		final String customer = bankslipValidator.getCustomer("TESTE", null);
		assertTrue(str.equals(customer));
	}
	
	@Test
	public void getCustomerTestInvalid(){
		final StringBuilder sbMessage = new StringBuilder();
		bankslipValidator.getCustomer(" ", sbMessage);
		assertTrue(sbMessage.toString().contains("Field customer is a required field"));
	}
	
	@Test
	public void getUUIDTestOk(){
		final String id = UUID.randomUUID().toString();
		final UUID uuid = bankslipValidator.getUUID(id);
		assertTrue(id.equals(uuid.toString()));
	}
	
	@Test(expected = UUIDInvalidException.class)
	public void getUUIDTestException(){
		bankslipValidator.getUUID("5");
	}
	
	@Test
	public void validEntityTestOk(){
		final Bankslip bankslip = new Bankslip();
		final Boolean valid = bankslipValidator.validEntity(bankslip);
		assertTrue(valid);
	}
	
	@Test(expected = NotFoundEntityException.class)
	public void validEntityTestException(){
		bankslipValidator.validEntity(null);
	}
	
	@Test
	public void validIntegerValueOk(){
		final Boolean valid = bankslipValidator.validIntegerValue(10);
		assertTrue(valid);
	}
	
	@Test(expected = FieldInvalidValueException.class)
	public void validIntegerValueException(){
		bankslipValidator.validIntegerValue(0);
	}
	
	@Test
	public void validRegexDateOk() throws ParseException{
		final Boolean valid = bankslipValidator.validRegexDate("2018-05-07");
		assertTrue(valid);
	}
	
	@Test(expected = ParseException.class)
	public void validRegexDateException() throws ParseException{
		bankslipValidator.validRegexDate("07-05-2018");
	}
	
}
