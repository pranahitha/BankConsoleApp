package com.revature.training.pms.model;

public class Transaction {

	private int transactionId;
	private int customerId;
	
	private int depositedAmount;
	private int withdrawnAmount;
	private int transferredAmount;
	private int transferredToCustId;
	private String comment;

	public Transaction(int transactionId, int customerId, int depositedAmount, int withdrawnAmount,
			int transferredAmount, int transferredToCustId, String comment) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.depositedAmount = depositedAmount;
		this.withdrawnAmount = withdrawnAmount;
		this.transferredAmount = transferredAmount;
		this.transferredToCustId = transferredToCustId;
		this.comment = comment;
	}

	public Transaction() {
	}

	public Transaction( int customerId, int depositedAmount) {
		super();		
		this.customerId = customerId;
		
		this.depositedAmount = depositedAmount;
		
	}

	public Transaction( int customerId, int withdrawnAmount,String comment) {
		super();		
		this.customerId = customerId;
		
		this.withdrawnAmount=withdrawnAmount;
		this.comment=comment;
	}
	
	
	

	public Transaction( int customerId, int transferredAmount,int transferredToCustId) {
		super();		
		this.customerId = customerId;
		
		this.transferredAmount = transferredAmount;
		this.transferredToCustId=transferredToCustId;
		
	}
	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	

	

	public int getDepositedAmount() {
		return depositedAmount;
	}

	public void setDepositedAmount(int depositedAmount) {
		this.depositedAmount = depositedAmount;
	}

	public int getWithdrawnAmount() {
		return withdrawnAmount;
	}

	public void setWithdrawnAmount(int withdrawlamount) {
		this.withdrawnAmount = withdrawlamount;
	}

	public int getTransferredAmount() {
		return transferredAmount;
	}

	public void setTransferredAmount(int transferredAmount) {
		this.transferredAmount = transferredAmount;
	}

	public int getTransferredToCustId() {
		return transferredToCustId;
	}

	public void setTransferredToCustId(int transferredToCustId) {
		this.transferredToCustId = transferredToCustId;
	}

	@Override
	public String toString() {
		return "\nTransaction [transactionId=" + transactionId + ", customerId=" + customerId + ", depositedAmount=" + depositedAmount + ", withdrawnAmount=" + withdrawnAmount
				+ ", transferredAmount=" + transferredAmount + ", transferredToCustId=" + transferredToCustId + "]";
	}
	
	

}
