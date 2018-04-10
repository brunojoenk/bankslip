package com.joenk.bankslip.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joenk.bankslip.constants.Constants;
import com.joenk.bankslip.exception.FieldInvalidValueException;
import com.joenk.bankslip.exception.NotFoundEntityException;
import com.joenk.bankslip.exception.UUIDInvalidException;
import com.joenk.bankslip.model.BankslipDTO;
import com.joenk.bankslip.model.ReturnMessageDTO;
import com.joenk.bankslip.service.BankslipService;

@RestController
@RequestMapping(value = Constants.PATH_BANKSLIP)
public class BankslipController {

	@Autowired
	private BankslipService bankslipService;
	
	@PostMapping
	public ResponseEntity<ReturnMessageDTO> create(@RequestBody @Valid final BankslipDTO bankslipDTO) {
		bankslipService.create(bankslipDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ReturnMessageDTO.Builder().build("Bankslip Created", bankslipDTO));
	}
	
	@GetMapping
	public ResponseEntity<ReturnMessageDTO> listAll() {
		final List<BankslipDTO> bankslipDTOs = bankslipService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessageDTO.Builder().build("Ok", bankslipDTOs));
	}
	
	@GetMapping(Constants.PATH_BANKSLIP_GET_BY_ID)
	public ResponseEntity<ReturnMessageDTO> getById(@PathVariable(value = Constants.PARAM_ID) final String bankslipId) {
		final BankslipDTO bankslipDTO = bankslipService.getById(bankslipId);
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnMessageDTO.Builder().build("Ok", bankslipDTO));
	}

	@PutMapping(Constants.PATH_BANKSLIP_PAY)
	public ResponseEntity<ReturnMessageDTO> pay(@PathVariable(value = Constants.PARAM_ID)final String bankslipId) {
		bankslipService.pay(bankslipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ReturnMessageDTO.Builder().build("Bankslip paid"));
	}

	@DeleteMapping(Constants.PATH_BANKSLIP_CANCEL)
	public ResponseEntity<ReturnMessageDTO> cancel(@PathVariable(value = Constants.PARAM_ID)final String bankslipId) {
		bankslipService.cancel(bankslipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ReturnMessageDTO.Builder().build("Bankslip canceled"));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ReturnMessageDTO> handleHttpMessageNotReadableException() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessageDTO.Builder().build("Bankslip not provided in the request body"));
	}
	
	@ExceptionHandler(UUIDInvalidException.class)
	public ResponseEntity<ReturnMessageDTO> handleUUIDInvalidException(final UUIDInvalidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReturnMessageDTO.Builder().build(ex.getMessageErroṛ()));
	}

	@ExceptionHandler(FieldInvalidValueException.class)
	public ResponseEntity<ReturnMessageDTO> handleFieldInvalidValueException(final FieldInvalidValueException ex) {
		final StringBuilder sbErrorMessage = new StringBuilder("Invalid bankslip provided, The possible reasons are:");
		sbErrorMessage.append(ex.getMessageErroṛ());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ReturnMessageDTO.Builder().build(sbErrorMessage.toString()));
	}
	
	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity<ReturnMessageDTO> handleNotFoundEntityException(final NotFoundEntityException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnMessageDTO.Builder().build(ex.getMessageErroṛ()));
	}
	
}
