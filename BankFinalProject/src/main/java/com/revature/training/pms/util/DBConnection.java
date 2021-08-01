package com.revature.training.pms.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	/*
	  public static Connection getConnection() { 
		  Connection connection=null;
	  
	  
	  try { 
		  Class.forName("org.postgresql.Driver");
	  System.out.println("Postgresql driver loaded success");
	  connection=DriverManager.getConnection(
	  "jdbc::postgresql://localhost::5432/postgres","postgres","root");
	  } catch
	  (ClassNotFoundException e) {
		  e.printStackTrace(); 
		  } catch (SQLException e) {
	  // TODO Auto-generated catch block e.printStackTrace(); } return connection;
	  
	  }
	return connection;
	  }
}
	 */

	  public static Connection getDBConnection()
	  {
		  Connection connection = null;
	  Properties properties = new Properties(); 
	  try { FileReader reader = new FileReader("jadala2.properties");
	  properties.load(reader); }
	  catch
	  (FileNotFoundException e1) { 
		  e1.printStackTrace(); 
		  } catch (IOException e) {
			  e.printStackTrace(); }
	  
	  String driver =null;
	  String url =null;
	  String username =null;
	  String password=null;
	  
	  driver= properties.getProperty("driver"); 
	  url= properties.getProperty("url");
	  username= properties.getProperty("username");
	  password= properties.getProperty("password");
	  
	  try {
		  Class.forName(driver); // Type 4 driver
	  
	  connection = DriverManager.getConnection(url,username,password);
	  } catch
	  (SQLException e) {
		  e.printStackTrace(); 
		  } catch (ClassNotFoundException e) {
			  	e.printStackTrace(); }
	  
	  return connection; 
	  }

}	 


