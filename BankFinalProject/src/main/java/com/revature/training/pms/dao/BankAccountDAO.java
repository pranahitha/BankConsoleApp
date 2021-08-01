package com.revature.training.pms.dao;

import java.util.List;

import com.revature.training.pms.model.BankModel;

public interface BankAccountDAO{
	
	public boolean createAccount(BankModel bank);//
	public void withdrawAmount(int accountNo,int amount);		//customer withdraws
	public void  depositAmount(int accountNo,int amount);		//customer deposits amount
	public void amountTransfer(int accountNo,int amountTransfer,int receiverAccountNo);		//customer transfers amount
	public int viewBalance(int accountNo);		///customer can view balance amount
	public boolean isAccountExists(int accountNo);	//
	public List<BankModel> viewAllUsersData();
	public boolean removeAccount(int accountNo);
	
}
