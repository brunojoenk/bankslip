package com.joenk.bankslip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.joenk.bankslip.enums.Status;

@Entity @Table(name = "BANKSLIP")
public class Bankslip {

    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "total_in_cents")
    private Integer totalInCents;

    @Column(name = "customer")
    private String customer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getTotalInCents() {
		return totalInCents;
	}

	public void setTotalInCents(Integer totalInCents) {
		this.totalInCents = totalInCents;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
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
		Bankslip other = (Bankslip) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
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
		return "Bankslip [id=" + id + ", dueDate=" + dueDate + ", totalInCents=" + totalInCents + ", customer="
				+ customer + ", status=" + status + "]";
	}	
}
