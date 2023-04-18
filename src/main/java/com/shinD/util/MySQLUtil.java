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
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
 		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("1. driver load 성공"); 
		
		String url = "jdbc:mysql://127.0.0.1/ShinD";
		String userid="ShinD", pass="1234";
		Connection conn = DriverManager.getConnection(url, userid, pass);
		System.out.println("2. Connection 성공");
		  
	}
	
 	public static Connection getConnection() {
		Context initContext;
		Connection conn = null;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/sqlserver");
 			 conn = ds.getConnection();
 		} catch (NamingException e) {
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
