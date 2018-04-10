package com.joenk.bankslip.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = ReturnMessageDTO.Builder.class)
public class ReturnMessageDTO {
	
	private String message;
	private Object bankslip;
	
	private ReturnMessageDTO(Builder builder) {
		this.message = builder.message;
		this.bankslip = builder.bankslip;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
	public static class Builder {

		private String message;
		private Object bankslip;
	    
		public Builder message(final String message) {
			this.message = message;
			return this;
		}
		
		public Builder bankslip(final Object bankslip) {
			this.bankslip = bankslip;
			return this;
		}
		
		public ReturnMessageDTO build() {
			return new ReturnMessageDTO(this);
		}
		
		public ReturnMessageDTO build(final String message) {
			this.message = message;
			return new ReturnMessageDTO(this);
		}
		
		public ReturnMessageDTO build(final String message, final Object bankslip) {
			this.message = message;
			this.bankslip = bankslip;
			return new ReturnMessageDTO(this);
		}
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBankslip() {
		return bankslip;
	}

	public void setBankslip(Object bankslip) {
		this.bankslip = bankslip;
	}

}
