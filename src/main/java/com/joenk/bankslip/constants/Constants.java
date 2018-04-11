package com.joenk.bankslip.constants;

public class Constants {
	
	public static final String PATH_BANKSLIP = "/rest/bankslips";
			
	public static final String PATH_BANKSLIP_GET_BY_ID = "/{id}";
	
	public static final String PATH_BANKSLIP_PAY = "/{id}/pay";
	
	public static final String PATH_BANKSLIP_CANCEL = "/{id}/cancel";
	
	public static final String PARAM_ID = "id";
	
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
	
	
	public static final String MSG_BANKSLIP_CREATED = "Bankslip Created";
	
	public static final String MSG_BANKSLIP_PAID = "Bankslip paid";
	
	public static final String MSG_BANKSLIP_CANCELED = "Bankslip canceled";
	
	public static final String MSG_BANKSLIP_NOT_PROVIDED = "Bankslip not provided in the request body";
	
	public static final String MSG_INVALID_BANKSLIP_PROVIDED = "Invalid bankslip provided, The possible reasons are:";
	
	public static final String MSG_ERROR_PATTERN_DATE = "Field due_date must be pattern YYYY-MM-DD";
	
	public static final String MSG_ERROR_ONLY_NUMBER = "Only number is valid";
	
	public static final String MSG_ERROR_STAUTUS_INVALID = "Field status must be PENDING, PAID or CANCELED";
	
	public static final String MSG_ERROR_UUID_INVALID = "Invalid id provided - it must be a valid UUID";
	
	public static final String MSG_ERROR_BANKSLIP_NOT_FOUND = "Bankslip not found with the specified id";
	
	public static final String MSG_ERROR_INTEGER_GREATER_0 = "The number must be greater than 0";
	
	
	public static final String TABLE_BANKSLIP = "BANKSLIP";
	
	
	public static final String FIELD_NAME_ID = "id";
	
	public static final String FIELD_NAME_DUE_DATE = "due_date";
	
	public static final String FIELD_NAME_TOTAL_IN_CENTS = "total_in_cents";
	
	public static final String FIELD_NAME_STATUS = "status";
	
	public static final String FIELD_NAME_CUSTOMER = "customer";
	
	public static final String FIELD_NAME_FINE = "fine";
	
	
	
	public static String getMsgRequiredField(final String field){
		return "Field " + field + " is a required field";
	}
		
}
