package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoDatabaseConnection {
	public static Connection getConnection() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = 
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","satish","sats");
		
		return conn;
	}
}
