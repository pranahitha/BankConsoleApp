package com.revature.training.pms.model;

import java.io.Serializable;
import java.sql.ResultSet;

public class BankModel implements Serializable {
	private int accountNo;
	private String customerName;
	private int accountBalance;
	private String loan;
	private String branch;
	
	//default constructor
	public BankModel()
	{
	}
	//parameterised constructor
	
	
	public BankModel(int accountNo, String customerName, int accountBalance, String loan, String branch) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.accountBalance = accountBalance;
		this.loan = loan;
		this.branch = branch;
	}

	/*
	 * public BankModel(int accountNo) { super(); this.accountNo = accountNo; }
	 */


	//getters and setters
	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	//print customer details
	@Override
	public String toString() {
		return "\n Bank [accountNo= " + accountNo + ", customerName= " + customerName + ", accountBalance= " + accountBalance
				+ ", loan= " + loan + ", branch= " + branch + "]\n";
	}
	
	
	
}
