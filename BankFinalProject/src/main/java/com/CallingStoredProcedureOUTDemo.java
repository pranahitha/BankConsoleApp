package com;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.revature.training.pms.util.DBConnection;


	public class CallingStoredProcedureOUTDemo {
		public static void main(String[] args) {
			Connection connection=DBConnection.getDBConnection();
			int debitorBalance=0,creditorBalance=0;
			
			try {
				CallableStatement statement = connection.prepareCall("call hr.transfermoney(?,?,?,?,?)");
				statement.setInt(1, 2);
				statement.setInt(2,1);
				statement.setInt(3,300);
				statement.registerOutParameter(4,Types.INTEGER);
				statement.setInt(4,debitorBalance);
				statement.registerOutParameter(5,Types.INTEGER);
				statement.setInt(5,creditorBalance);
				statement.execute();
				debitorBalance=statement.getInt(4);
				creditorBalance=statement.getInt(5);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Amount of INR 700 transferred from 2 to 1 and the balance after transaction is :");
			System.out.println("DEBIT BALANCE : "+debitorBalance);
			System.out.println("CREDIT BALANCE : "+creditorBalance);
			
		}

	}

