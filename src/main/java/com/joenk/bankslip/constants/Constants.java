package com.joenk.bankslip.constants;

public class Constants {
	
	public static final String PATH_BANKSLIP = "/rest/bankslips";
			
	public static final String PATH_BANKSLIP_GET_BY_ID = "/{id}";
	
	public static final String PATH_BANKSLIP_PAY = "/{id}/pay";
	
	public static final String PATH_BANKSLIP_CANCEL = "/{id}/cancel";
	
	public static final String PARAM_ID = "id";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
		
}
