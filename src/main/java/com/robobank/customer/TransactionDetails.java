package com.robobank.customer;

import java.math.BigDecimal;

public class TransactionDetails {

	Long referenceNumber;
	String accountNumber;
	String description;
	BigDecimal startBalance;
	BigDecimal mutation;
	BigDecimal endBalance;
	
	public Long getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(Long referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}
	
	@Override
	public String toString() {
		return "TransactionDetails [referenceNumber=" + referenceNumber + ", accountNumber=" + accountNumber
				+ ", description=" + description + ", startBalance=" + startBalance + ", mutation=" + mutation
				+ ", endBalance=" + endBalance + "]";
	}
}
