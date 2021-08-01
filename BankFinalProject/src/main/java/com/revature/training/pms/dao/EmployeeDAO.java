package com.revature.training.pms.dao;

import java.util.List;

import com.revature.training.pms.model.BankModel;
import com.revature.training.pms.model.EmployeeModel;



public interface EmployeeDAO {
	
	public boolean createEmployeeAccount(EmployeeModel empmodel);
	public boolean deleteUsersAccount(int accountNo);
	public boolean updateUsersAccount(BankModel bank);
	public List<BankModel> viewAllUsersData();
	public BankModel getCustomerByAccountNo(int accountNo);
	public boolean existEmployee(int employeeId);
	
}
