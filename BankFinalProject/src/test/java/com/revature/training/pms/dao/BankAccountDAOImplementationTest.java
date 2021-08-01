package com.revature.training.pms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.training.pms.model.BankModel;

public class BankAccountDAOImplementationTest {

	private BankAccountDAO bankDAO=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankDAO=new BankAccountDAOImplementation();
	}

	@After
	public void tearDown() throws Exception {
		bankDAO=null;
	}

	@Test
	public void testviewAllUsersData() {
		int accountNoTest=00;
		List<BankModel> original=bankDAO.viewAllUsersData();
		bankDAO.createAccount(new BankModel(accountNoTest,"qr",900,"null","rdm"));
		List<BankModel> original2=bankDAO.viewAllUsersData();
		assertEquals(original2.size(),original.size()+1);
		bankDAO.removeAccount(accountNoTest);
		
	}

}
