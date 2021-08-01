package com;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.revature.training.pms.dao.BankAccountDAO;
import com.revature.training.pms.dao.BankAccountDAOImplementation;
import com.revature.training.pms.exceptions.InsufficientAmountException;
import com.revature.training.pms.exceptions.InvalidException;
import com.revature.training.pms.model.BankModel;

public class UserBankApp {
	int choice=0;
	int accountNumber,amount;
	Scanner sc=new Scanner(System.in);
	boolean result;
	BankModel bank=new BankModel();
	BankAccountDAO accountDAO = new BankAccountDAOImplementation();
	private static Logger logger=Logger.getLogger("MainApp");

	public void startBankapp()
	{
		System.out.println("Welcome to Titli's Bank App");
		logger.info(" user app started running ");
		System.out.println("New User?? then please enter 'yes' else 'no' ");
		String response=sc.next();
		int accountNo=0;
		String username;
		String password;
		if(response.equals("yes"))
		{
			System.out.println("Please Enter your Full Name:");
			username =sc.next();
			System.out.println("Enter your Account Number: ");
			accountNo = sc.nextInt();
			System.out.println("Enter password :");
			password=sc.next();
			System.out.println("Re-enter password: ");
			String confirmPassword = sc.next();
			if(password.equals(confirmPassword))
			{
				System.out.println("Account Created successfully!");
			}
			else
			{
				System.out.println("Incorrect Password or UserName");
			}
			//function->user register into 
		}
		else if(response.equals("no"))
		{
			System.out.println("To log in enter your details");
			System.out.println("Please Enter your Full Name:");
			username=sc.next();
			System.out.println("Enter your password :");
			password=sc.next();		
			System.out.println("====== User logged in successfully=====");
		}
		while (true) {
			System.out.println();
			System.out.println("////// M E N U /////");
			System.out.println();
			System.out.println("Enter 1 to add your account into app database");
			System.out.println("Enter 2 to view Account Balance ");//login
			System.out.println("Enter 3 to Withdraw Amount ");	//register
			System.out.println("Enter 4 to View all Accounts");
			System.out.println("Enter 5 to Deposit  Amount");
			System.out.println("Enter 6 to delete account");
			System.out.println("enter 7 to transfer amount");
			System.out.println("enter 8 to exit");
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();

			switch(choice) {
			case 1:
				//logger
				System.out.println("===== Account Details =====");
				bank =acceptAccountDetails();
				if(accountDAO.isAccountExists(bank.getAccountNo()))
					{
						System.out.println("Account is :"+bank.getAccountNo() +" already exists with name : "+bank.getCustomerName());
						}
				else {
					result =accountDAO.createAccount(bank);
					if(result)
					{
						System.out.println("Customer Name is :" +bank.getCustomerName()+" updated into database");
					}
				else {
					System.out.println("Customer name not updated!"+bank.getCustomerName()+" with Account number: "+bank.getAccountNo());
				}
				}
				break;
			case 2:
				//logger
				System.out.println("==== View balance ====");
				System.out.println("Enter your Account Number: ");
				accountNumber=sc.nextInt();
				int balance=accountDAO.viewBalance(accountNumber);
				System.out.println("Your Current Balance is ---->"+balance);
				break;
			case 3:
				//logger
				System.out.println("==== Withdraw Amount ====");
				System.out.println("Enter your Account Number: ");
				accountNumber=sc.nextInt();
				System.out.println("Enter the amount you want to withdraw: ");
				amount=sc.nextInt();
				accountDAO.withdrawAmount(accountNumber,amount);				
				break;
			case 4:
				//logger
				System.out.println("==== View All Customers ====");
				System.out.println("Enter customer account Number:");
				accountNo=sc.nextInt();
				List<BankModel> accounts=  accountDAO.viewAllUsersData();
				System.out.println("###Printing all the customers");
				System.out.println(accounts);
				System.out.println();
				break;
			case 5:
							
				logger.info(" deposit amount ");
				System.out.println("===== Deposit Amount =====");
				System.out.println("Enter your Account Number: ");
				accountNumber=sc.nextInt();
				System.out.println("Please Enter the amount you want to deposit ==>");		
				int amount = sc.nextInt();
				try {
					accountDAO.depositAmount(accountNumber, amount);
				}catch (InvalidException e) {
					
					System.out.println(e);
				}catch (InsufficientAmountException e) {
					
					System.out.println(e);
				}
				break;
			case 6:
				//logger
				System.out.println("==== Delete Customer ====");
				//System.out.println("Enter customer account Number to remove:");
				
				bank= deleteAccount();
				System.out.println("Account Deleted");
				
				break;
			case 7:
				System.out.println("=====Transfer Amount=====");
				System.out.println("Enter your Account Number: ");
				accountNumber=sc.nextInt();
				System.out.println("Please Enter the amount you want to transfer ");		
				int amountToTransfer = sc.nextInt();
				System.out.println("Enter the receiver account number: ");
				int recieveraccountNo=sc.nextInt();
				try {
					accountDAO.amountTransfer(accountNumber, amountToTransfer, recieveraccountNo);
				}catch (InvalidException e) {
					
					System.out.println(e);
				}catch (InsufficientAmountException e) {
					
					System.out.println(e);
				}
				
				break;
			case 8:
				logger.info("app execution ended");
				System.out.println("//////// Thanks for Using App //////");
				System.exit(0);
			default:
				logger.info(" entered invalid key ");
				System.out.println("Invalid operation ");
				break;
			}
			}
	}
	public BankModel acceptAccountDetails() {
		System.out.println("Please enter Account no");
		int accountNo=sc.nextInt();
		System.out.println("Please enter your Name: ");
		String customerName=sc.next();
		System.out.println("Please enter current Balance");
		int accountBalance=sc.nextInt();
		System.out.println("Please mention loan if any or else enter as null ");
		String loan=sc.next();
		System.out.println("Please mention your bank branch: ");
		String branch=sc.next();
		
		BankModel bank=new BankModel(accountNo,customerName,accountBalance,loan,branch);
		return bank;
	}
	private BankModel deleteAccount() {
		// TODO Auto-generated method stub
		System.out.println("Please enter id to remove: ");
		int custId = sc.nextInt();
		
		System.out.println("Please enter customer name : ");
		String cName = sc.next();
		System.out.println("Please enter amount: ");
		int bal = sc.nextInt();
		System.out.println("Please enter loan if any  : ");
		String loan = sc.next();
		System.out.println("Please enter places ");
		String place = sc.next();

		BankModel bank = new BankModel(custId,cName,bal,loan,place);
		return bank;
	}

	
}
