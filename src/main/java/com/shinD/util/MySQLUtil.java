package com.shinD.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySQLUtil {
	
 	public static Connection getConnection() {
 		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1/ShinD";
		String userid="ShinD", pass="1234";

		Context initContext;
		try {
	 		Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userid, pass);
	 		 
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
		}catch (SQLException e) {
 			e.printStackTrace();
		}
		return conn; 
	}
	
  
	public static void dbDisconnect(ResultSet rs, Statement st, Connection conn) {
		try {
			if(rs!=null)  rs.close();
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
 			e.printStackTrace();
		}
	}
	 
	
}
