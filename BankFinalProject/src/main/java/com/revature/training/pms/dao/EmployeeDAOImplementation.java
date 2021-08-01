package com.revature.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pms.util.DBConnection;
import com.revature.training.pms.model.BankModel;
import com.revature.training.pms.model.EmployeeModel;

public class EmployeeDAOImplementation implements EmployeeDAO{

	Connection connection=DBConnection.getDBConnection();
	
	private final String CREATE_EMPLOYEE_ACCOUNT_QUERY="insert into hr.bankemployee values(?,?,?,?)";
	private final String DELETE_ACCOUNT_QUERY = "delete from hr.bankcustomer where  accountNo= ?";
	private final String UPDATE_ACCOUNT_QUERY = "update hr.bankcustomer set accountNo = ?,customerName=? ,accountBalance = ? , loan = ?, branch=?  where accountNo = ?";
	private final String FIND_ALL_CUSTOMERS = "select * from hr.bankcustomer";
	private final String GET_USER_ACCOUNT_BY_ID="select * from hr.bankcustomer where accountNo=?";
	private final String GET_EMPLOYEE_DETAILS_QUERY="select * from hr.bankemployee where empId=?";
	public boolean createEmployeeAccount(EmployeeModel empmodel) {

		int res=0;
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(CREATE_EMPLOYEE_ACCOUNT_QUERY);
			statement.setInt(1,empmodel.getEmpId());
			statement.setString(2,empmodel.getEmployeeName());
			statement.setInt(3, empmodel.getDeptId());
			statement.setString(4,empmodel.getDeptName());
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

	public boolean deleteUsersAccount(int accountNo) {
		
		boolean  result=false;
		try {
			PreparedStatement stat=connection.prepareStatement(DELETE_ACCOUNT_QUERY);
			stat.setInt(1, accountNo);
			int rst=stat.executeUpdate();
			result=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateUsersAccount(BankModel bank) {
		// TODO Auto-generated method stub
		int res=0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_QUERY);
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

	public BankModel getCustomerByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		
		BankModel bankmodel=new BankModel();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(GET_USER_ACCOUNT_BY_ID);
			statement.setInt(1, accountNo);
			ResultSet resultSet=statement.executeQuery();
			resultSet.next();
			bankmodel.setAccountNo(resultSet.getInt("accountNo"));
			bankmodel.setCustomerName(resultSet.getString("customerName"));
			bankmodel.setAccountBalance(resultSet.getInt("accountBalance"));
			bankmodel.setLoan(resultSet.getString("loan"));
			bankmodel.setBranch(resultSet.getString("branch"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return bankmodel;
	
		
	
	
	}

	public boolean existEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		EmployeeModel emp=new EmployeeModel();

		try {
			 PreparedStatement statement = connection.prepareStatement(GET_EMPLOYEE_DETAILS_QUERY);
			statement.setInt(1, employeeId);
			ResultSet res = statement.executeQuery();
			res.next();
			
			emp.setEmpId(res.getInt("empId"));
			emp .setEmployeeName(res.getString("employeeName"));
			emp.setDeptId(res.getInt("deptId"));
			emp.setDeptName(res.getString("deptName"));

			if (res.next()) {
				result = true;
			}
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
