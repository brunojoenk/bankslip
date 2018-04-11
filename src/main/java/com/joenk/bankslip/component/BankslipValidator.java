package com.joenk.bankslip.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.joenk.bankslip.constants.Constants;
import com.joenk.bankslip.entity.Bankslip;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.exception.FieldInvalidValueException;
import com.joenk.bankslip.exception.NotFoundEntityException;
import com.joenk.bankslip.exception.UUIDInvalidException;
import com.joenk.bankslip.model.BankslipDTO;

@Component
public class BankslipValidator {
	
	public Bankslip converDTOtoEntity(final BankslipDTO bankslipDTO) throws FieldInvalidValueException{
		final Bankslip bankslip = new Bankslip();
		final StringBuilder sbMessage = new StringBuilder();		
		bankslip.setId(bankslipDTO.getId());
		bankslip.setDueDate(getDueDate(bankslipDTO.getDueDate(), sbMessage));
		bankslip.setStatus(getStatus(bankslipDTO.getStatus(), sbMessage));
		bankslip.setTotalInCents(getTotalInCents(bankslipDTO.getTotalInCents(), sbMessage));
		bankslip.setCustomer(getCustomer(bankslipDTO.getCustomer(), sbMessage));	
		validTextMessageError(sbMessage);
		return bankslip;
	}
	
	public BankslipDTO convertEntityToDto(final Bankslip bankslip){
		final BankslipDTO bankslipDTO = new BankslipDTO();
		bankslipDTO.setId(bankslip.getId());
		bankslipDTO.setDueDate(getTextDueDate(bankslip.getDueDate()));
		bankslipDTO.setStatus(bankslip.getStatus().name());
		bankslipDTO.setTotalInCents(bankslip.getTotalInCents().toString());
		bankslipDTO.setCustomer(bankslip.getCustomer());
		return bankslipDTO;
	}
	
	public Date getDueDate(final String dueDateText, final StringBuilder sbMessage){		
		try {
			validTextField(dueDateText, Constants.FIELD_NAME_DUE_DATE);
			validRegexDate(dueDateText);
			final DateFormat formatter = new SimpleDateFormat(Constants.DATE_PATTERN);
			final Date date = formatter.parse(dueDateText);
			return date;
		} 
		catch (ParseException e) {
			sbMessage.append(Constants.MSG_ERROR_PATTERN_DATE);
		}
		catch (FieldInvalidValueException e) {
			sbMessage.append(e.getMessageErroṛ());
		}
		return null;
	}
	
	public String getTextDueDate(final Date dueDate){
		final DateFormat df = new SimpleDateFormat(Constants.DATE_PATTERN);       
		final String reportDate = df.format(dueDate);
		return reportDate;
	}
	
	public Status getStatus(final String statusText, final StringBuilder sbMessage) {
		try{
			validTextField(statusText, Constants.FIELD_NAME_STATUS);
			final Status status = Status.valueOf(statusText);
			return status;
		}catch(IllegalArgumentException e){
			sbMessage.append(Constants.MSG_ERROR_STAUTUS_INVALID);
		}
		return null;
	}
	
	public Integer getTotalInCents(final String totalInCentsText, final StringBuilder sbMessage){
		try{
			validTextField(totalInCentsText, Constants.FIELD_NAME_TOTAL_IN_CENTS);
			final Integer totalInCents = Integer.valueOf(totalInCentsText);
			validIntegerValue(totalInCents);
			return totalInCents;
		}
		catch(NumberFormatException e){
			sbMessage.append(Constants.MSG_ERROR_ONLY_NUMBER);
		}
		catch (FieldInvalidValueException e) {
			sbMessage.append(e.getMessageErroṛ());
		}
		return null;
	}
	
	public String getCustomer(final String customerText, final StringBuilder sbMessage){
		try{
			validTextField(customerText, Constants.FIELD_NAME_CUSTOMER);
			return customerText;
		}
		catch (FieldInvalidValueException e) {
			sbMessage.append(e.getMessageErroṛ());
		}
		return null;
	}
	
	public UUID getUUID(final String id){
		try{
			final UUID uuid = UUID.fromString(id);
			return uuid;
		}
		catch(IllegalArgumentException e){
			throw new UUIDInvalidException(Constants.MSG_ERROR_UUID_INVALID);
		}
	}
	
	public Boolean validEntity(final Bankslip bankslip) {
		if(bankslip == null) {
			throw new NotFoundEntityException(Constants.MSG_ERROR_BANKSLIP_NOT_FOUND);
		}
		return true;
	}
	
	public Boolean validTextMessageError(final StringBuilder sbMessage) throws FieldInvalidValueException{
		if(StringUtils.hasText(sbMessage)){
			throw new FieldInvalidValueException(sbMessage.toString());
		}
		return true;
	}
	
	public Boolean validTextField(final String text, final String field){
		if(!StringUtils.hasText(text)){
			throw new FieldInvalidValueException(Constants.getMsgRequiredField(field));
		}
		return true;
	}
	
	public Boolean validIntegerValue(final Integer integer){
		if(integer < 1){
			throw new FieldInvalidValueException(Constants.MSG_ERROR_INTEGER_GREATER_0);
		}
		return true;
	}
	
	public Boolean validRegexDate(final String dateStr) throws ParseException{
		if(!dateStr.matches(Constants.DATE_REGEX)){
			throw new ParseException(dateStr, 0);
		}
		return true;
	}
	
	public Boolean validDTO(final BankslipDTO bankslipDTO){
		if(bankslipDTO == null){
			throw new HttpMessageNotReadableException(Constants.MSG_BANKSLIP_NOT_PROVIDED);
		}
		return true;
	}
	
}
