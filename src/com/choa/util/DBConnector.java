package com.choa.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {

	public Connection getConnector() throws Exception {
		String user="hr";
		String password="tiger";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String driver ="oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		System.out.println("드라이버 연결");
		
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
		
	}
}
