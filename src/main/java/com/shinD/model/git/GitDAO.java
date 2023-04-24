package com.shinD.model.git;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinD.util.MySQLUtil;

public class GitDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	int result = 0;
	
	//깃 등록하기
	public int registerGit(int user_code, String git_id) {
		String sql  = "insert into github(user_code,git_id) values( ? ,? )";
		conn = MySQLUtil.getConnection();
		
		System.out.println("git 등록하러 옴");
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_code);
			pst.setString(2, git_id);
			
			result = pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, pst, conn);
		}
		
		return result;
	}
}
