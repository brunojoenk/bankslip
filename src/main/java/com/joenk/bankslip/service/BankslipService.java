package com.joenk.bankslip.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.joenk.bankslip.component.BankslipValidator;
import com.joenk.bankslip.component.FineCalculate;
import com.joenk.bankslip.entity.Bankslip;
import com.joenk.bankslip.enums.Status;
import com.joenk.bankslip.exception.FieldInvalidValueException;
import com.joenk.bankslip.exception.NotFoundEntityException;
import com.joenk.bankslip.exception.UUIDInvalidException;
import com.joenk.bankslip.model.BankslipDTO;
import com.joenk.bankslip.repository.BankslipRepository;

@Service
public class BankslipService {
	
	@Autowired
	public BankslipRepository bankslipRepository;
	
	@Autowired 
	public BankslipValidator bankslipValidator;
	
	@Autowired
	public FineCalculate fineCalculate;
	
	public void create(final BankslipDTO bankslipDTO) {
		try{
			bankslipValidator.validDTO(bankslipDTO);
			bankslipDTO.setId(UUID.randomUUID().toString());
			final Bankslip entity = bankslipValidator.converDTOtoEntity(bankslipDTO);		
			bankslipRepository.save(entity);			
		}
		catch(FieldInvalidValueException e){
			throw e;
		}
		catch(HttpMessageNotReadableException e){
			throw e;
		}
	}
	
	public List<BankslipDTO> listAll() {
		final List<BankslipDTO> bankslipDTOs = new ArrayList<BankslipDTO>();
		try{			
			final List<Bankslip> bankslips = bankslipRepository.findAll();
			bankslips.forEach(bankslip -> bankslipDTOs.add(bankslipValidator.convertEntityToDto(bankslip)));
		}
		catch(Exception e){
			throw e;
		}
		return bankslipDTOs;
	}

	public Bankslip pay(final String id) {
		try{
			final Bankslip bankslip = getEntityById(id);
			bankslip.setStatus(Status.PAID);
			bankslipRepository.save(bankslip);
			return bankslip;
		}
		catch(NotFoundEntityException e){
			throw e;
		}
	}

	public Bankslip cancel(final String id) {
		try{
			final Bankslip bankslip = getEntityById(id);
			bankslip.setStatus(Status.CANCELED);
			bankslipRepository.save(bankslip);
			return bankslip;
		}
		catch(Exception e){
			throw e;
		}		
	}
	
	public BankslipDTO getById(final String id) {
		try{
			final UUID uuid = bankslipValidator.getUUID(id);
			final Bankslip bankslip = getEntityById(uuid.toString());
			final BankslipDTO bankslipDTO = bankslipValidator.convertEntityToDto(bankslip);
			bankslipDTO.setFine(String.valueOf(fineCalculate.calculateFine(
					LocalDate.now(), 
					bankslip.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
					bankslip.getTotalInCents())));
			return bankslipDTO;
		}
		catch(UUIDInvalidException e) {
			throw e;
		}
		catch(NotFoundEntityException e){
			throw e;
		}
		catch(FieldInvalidValueException e){
			throw e;
		}
	}
	
	public Bankslip getEntityById(final String id){
		try{
			final Bankslip bankslip = bankslipRepository.findOne(id);
			bankslipValidator.validEntity(bankslip);
			return bankslip;
		}
		catch(NotFoundEntityException e){
			throw e;
		}
	}
}
