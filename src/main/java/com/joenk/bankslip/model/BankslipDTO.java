package com.joenk.bankslip.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankslipDTO implements Serializable {
	
	private static final long serialVersionUID = -2194820752775365365L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("due_date")
    private String dueDate;
	
	@JsonProperty("total_in_cents")
    private String totalInCents;
	
	@JsonProperty("customer")
    private String customer;
	
	@JsonProperty("status")
    private String status;
	
	@JsonProperty("fine")
	private String fine;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getTotalInCents() {
		return totalInCents;
	}

	public void setTotalInCents(String totalInCents) {
		this.totalInCents = totalInCents;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((fine == null) ? 0 : fine.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalInCents == null) ? 0 : totalInCents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankslipDTO other = (BankslipDTO) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (fine == null) {
			if (other.fine != null)
				return false;
		} else if (!fine.equals(other.fine))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalInCents == null) {
			if (other.totalInCents != null)
				return false;
		} else if (!totalInCents.equals(other.totalInCents))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankslipDTO [id=" + id + ", dueDate=" + dueDate + ", totalInCents=" + totalInCents + ", customer="
				+ customer + ", status=" + status + ", fine=" + fine + "]";
	}
	
}
