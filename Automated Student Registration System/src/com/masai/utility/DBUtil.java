package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection provideConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
	     	System.out.println(e.getMessage());
 		}
		
		String url = "jdbc:mysql://localhost:3306/app_db";
		
		try {
		   conn = DriverManager.getConnection(url,"root","8762");
		} 
		catch (SQLException e) {
		   System.out.println(e.getMessage());
		}
		
		
		
		return conn;
		
	}

}
