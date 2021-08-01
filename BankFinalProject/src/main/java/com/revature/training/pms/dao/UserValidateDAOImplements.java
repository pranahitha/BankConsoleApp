package com.revature.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pms.util.DBConnection;
import com.revature.training.pms.exceptions.UserValidate;

public class UserValidateDAOImplements implements UserValidateDAO {
	Connection connection=DBConnection.getDBConnection();
	private final  String VALIDATE_USER_QUERY="select * from bankUser where  username = ? and password = ?";

	public boolean validateUser(UserValidate user) {
		boolean result=false;
		PreparedStatement statement=null;
		try {
			statement = connection.prepareStatement(VALIDATE_USER_QUERY);
			statement.setInt(1, user.getUserId());
			statement.setString(2,user.getUsername());
			statement.setString(3,user.getPassword());
			ResultSet res = statement.executeQuery();
			
			if(res.next())
			{
				//user is valid
				 result=true;
			}
			else
			{
				//user is invalid
				 result=false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return result;	
			
		
	}

}
