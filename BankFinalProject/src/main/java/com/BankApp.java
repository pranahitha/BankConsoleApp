package com;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
public class BankApp {
	
	int choice;
	Scanner sc= new Scanner(System.in);
	private static Logger logger=Logger.getLogger("BankApp");
	public void startMainApp()
	{
		
		System.out.println("Enter Your Name : ");
		String name=sc.nextLine();
		logger.info("Welcome "+name+" you logged in at "+ new Date());
		while(true)
		{
			logger.info(" app started running ");
		System.out.println("==================");
		System.out.println("Enter 1 for User");
		
		System.out.println("Enter 2 for Exit");
		System.out.println();
		System.out.println("Enter any key from Above");
		choice=sc.nextInt();
		switch (choice) {
		case 1:
			logger.info("User Ap Started ");
			UserBankApp userApp=new UserBankApp();
			userApp.startBankapp();
			
			break;
		case 2:
			
			logger.info("Main App Closed");
			System.out.println("//////Thank you for using Titli's Bank APP///////");
			System.exit(0);
			
			break;
			
		default:
			System.out.println("Wrong Choice ! Try Again");
			System.out.println();
			break;
		}
		}
	}

}
