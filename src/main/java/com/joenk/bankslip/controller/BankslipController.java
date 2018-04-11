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
import com.joenk.bankslip.service.BankslipService;

@RestController
@RequestMapping(value = Constants.PATH_BANKSLIP)
public class BankslipController {

	@Autowired
	public BankslipService bankslipService;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid final BankslipDTO bankslipDTO) {
		bankslipService.create(bankslipDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.MSG_BANKSLIP_CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Object> listAll() {
		final List<BankslipDTO> bankslipDTOs = bankslipService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(bankslipDTOs);
	}
	
	@GetMapping(Constants.PATH_BANKSLIP_GET_BY_ID)
	public ResponseEntity<Object> getById(@PathVariable(value = Constants.PARAM_ID) final String bankslipId) {
		final BankslipDTO bankslipDTO = bankslipService.getById(bankslipId);
		return ResponseEntity.status(HttpStatus.OK).body(bankslipDTO);
	}

	@PutMapping(Constants.PATH_BANKSLIP_PAY)
	public ResponseEntity<Object> pay(@PathVariable(value = Constants.PARAM_ID)final String bankslipId) {
		bankslipService.pay(bankslipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Constants.MSG_BANKSLIP_PAID);
	}

	@DeleteMapping(Constants.PATH_BANKSLIP_CANCEL)
	public ResponseEntity<Object> cancel(@PathVariable(value = Constants.PARAM_ID)final String bankslipId) {
		bankslipService.cancel(bankslipId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Constants.MSG_BANKSLIP_CANCELED);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.MSG_BANKSLIP_NOT_PROVIDED);
	}
	
	@ExceptionHandler(UUIDInvalidException.class)
	public ResponseEntity<Object> handleUUIDInvalidException(final UUIDInvalidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.MSG_ERROR_UUID_INVALID);
	}

	@ExceptionHandler(FieldInvalidValueException.class)
	public ResponseEntity<Object> handleFieldInvalidValueException(final FieldInvalidValueException ex) {
		final StringBuilder sbErrorMessage = new StringBuilder(Constants.MSG_INVALID_BANKSLIP_PROVIDED);
		sbErrorMessage.append(ex.getMessageErroṛ());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(sbErrorMessage.toString());
	}
	
	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity<Object> handleNotFoundEntityException(final NotFoundEntityException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MSG_ERROR_BANKSLIP_NOT_FOUND);
	}
	
}
