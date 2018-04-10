package com.joenk.bankslip.exception;

public class UUIDInvalidException extends RuntimeException {
	
	private String messageErroṛ;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UUIDInvalidException( final String error) {
        super(error);
        this.messageErroṛ = error;
    }

	public String getMessageErroṛ() {
		return messageErroṛ;
	}

	public void setMessageErroṛ(String messageErroṛ) {
		this.messageErroṛ = messageErroṛ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
        
}
