package com.revature.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.training.pms.exceptions.InsufficientAmountException;
import com.revature.training.pms.exceptions.InvalidException;
import com.revature.training.pms.model.BankModel;
import com.revature.training.pms.util.DBConnection;

public class BankAccountDAOImplementation implements BankAccountDAO{
	PreparedStatement statement;
	ResultSet resultset;
	
	Connection connection=DBConnection.getDBConnection();
	private final String ADD_CUSTOMER_ACCOUNT_QUERY = "insert into hr.bankcustomer values(?,?,?,?,?)";
	private final String FIND_CUSTOMER_BY_ACCOUNT_NO_QUERY = "select * from hr.bankcustomer where accountNo = ?";
	private final String VIEW_BALANCE_BY_ACCOUNT_NO_QUERY = "select accountNo,customerName,accountBalance from hr.bankcustomer where accountNo = ?";
	private final String WITHDRAW_QUERY="update hr.bankcustomer set accountBalance = accountBalance-?  where accountNo = ?";
	private final String DEPOSIT_AMOUNT_QUERY="update hr.bankcustomer set accountBalance = accountBalance+?  where accountNo = ?";
	private final String FIND_ALL_CUSTOMERS="select * from hr.bankcustomer ";
	private final String DELETE_CUSTOMER_QUERY="delete from hr.bankcustomer where accountNo = ?";
	//u n e
	public boolean createAccount(BankModel bank) {
		
		int res=0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_ACCOUNT_QUERY);
			statement.setInt(1,bank.getAccountNo());
			statement.setString(2,bank.getCustomerName());
			statement.setInt(3, bank.getAccountBalance());
			statement.setString(4,bank.getLoan());
			statement.setString(5,bank.getBranch());
			
			res=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res==0)
		{
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isAccountExists(int accountNo) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(FIND_CUSTOMER_BY_ACCOUNT_NO_QUERY);
			stat.setInt(1, accountNo);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	//user can view his current balance amount
	public int viewBalance(int accountNo) {
		int balanceAmount=0;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(VIEW_BALANCE_BY_ACCOUNT_NO_QUERY);
			statement.setInt(1, accountNo);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				balanceAmount=resultSet.getInt("accountBalance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(balanceAmount !=0)
			return balanceAmount;
		else
			System.out.println("Error Occured! Please try later or Zero Balance Amount!");
			return 0;
	}

	public void withdrawAmount(int accountNo, int amount) {
		// TODO Auto-generated method stub
		int res;
		if(amount<=0)
		{
			throw new InvalidException("Negative Account Balance ! Please Try Later");
		}
		else
		{
			int totalBalance = viewBalance(accountNo);
			if(totalBalance-amount <0)
				throw new InsufficientAmountException("Insufficient Balance");
				else
				{
					try {
						 statement = connection.prepareStatement(WITHDRAW_QUERY);
						
						statement.setInt(2, accountNo);
						statement.setInt(1, amount);
						res = statement.executeUpdate();
						if (res == 1)
							System.out.println("Amount of INR " + amount + " withdrawn Successfully");
						else
							System.out.println("Something went Wrong Try Again");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}

	public void depositAmount(int accountNo, int amount) {
		// TODO Auto-generated method stub
			int res= 0;
		
		
		if (amount <= 0) {
			
			throw new InvalidException("Negative  Balance ! Please try Again");
		}
		else {
			try {
				statement = connection.prepareStatement(DEPOSIT_AMOUNT_QUERY);
				statement.setInt(1, amount);
				statement.setInt(2, accountNo);
				res = statement.executeUpdate();
				if (res == 1)
					System.out.println("Amount of INR " + amount + " deposited Successfully");
				else
					System.out.println("Something went Wrong Try Again");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}

//callabale for transfer amount-----------//
public void amountTransfer(int accountNo, int amountTransfer, int receiverAccountNo) {
		// TODO Auto-generated method stub
		if (amountTransfer <= 0)
			throw new InvalidException("Negative  Balance ! Please try Again");
		else {
			int totalBalance = viewBalance(accountNo);
			if (totalBalance - amountTransfer < 0) {
				System.out.println("InSufficient Balance! Please try Again");
			} else {
				int debitorBalance=0,creditorBalance=0;
				try {
					CallableStatement statement=connection.prepareCall("call hr.transfermoney(?,?,?)");
					statement.setInt(1, accountNo);
					statement.setInt(2,receiverAccountNo);
					statement.setInt(3, amountTransfer);
					//statement.registerOutParameter(4, Types.INTEGER);
					/*
					 * statement.setInt(4, debitorBalance); statement.registerOutParameter(5,
					 * Types.INTEGER); statement.setInt(5, creditorBalance);
					 */
					statement.execute();
					ResultSet resultSet=statement.executeQuery();
					  debitorBalance=resultSet.getInt(2);
					  creditorBalance=resultSet.getInt(1);
					 
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Amount of INR " + amountTransfer + 
				" transferred from ID " + accountNo+ " to ID " + receiverAccountNo + " Successfully");
				System.out.println("//////Updated Balance/////////");
				System.out.println("Debitor's Balance id INR "+debitorBalance);
				System.out.println("Creditor's Balance INR "+creditorBalance);
			}
		}
	}

public List<BankModel> viewAllUsersData() {
	
	List<BankModel> bank1 = new ArrayList<BankModel>();
	try {

		Statement statement=connection.createStatement();
		ResultSet res=statement.executeQuery(FIND_ALL_CUSTOMERS);
		while(res.next())
		{
			BankModel bankmodel=new BankModel();
			bankmodel.setAccountNo(res.getInt("accountNo"));
			bankmodel.setCustomerName(res.getString("customerName"));
			bankmodel.setAccountBalance(res.getInt("accountBalance"));
			bankmodel.setLoan(res.getString("loan"));
			bankmodel.setBranch(res.getString("branch"));
			bank1.add(bankmodel);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bank1;
	
}

public boolean removeAccount(int accountNo) {
	// TODO Auto-generated method stub
	int r=0;
	//boolean res=false;
	
	try {
		PreparedStatement statement=connection.prepareStatement(DELETE_CUSTOMER_QUERY);
		statement.setInt(1,accountNo);
		r=statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	if(r==0)
	{
		return true;
	}else {
		return false;
	}

}



}
	
	



