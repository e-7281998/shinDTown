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

	//gitId 불러오기
	public String getGitId(String user_code) {
		String sql  = "select git_id from github where user_code = ?";
		conn = MySQLUtil.getConnection();
		String git_id = null;
		 
		try {
			pst = conn.prepareStatement(sql);
 			pst.setString(1, user_code);
			
 			rs = pst.executeQuery();
 			
 			while(rs.next()) {
 				git_id = rs.getString("git_id");
 			}
 			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, pst, conn);
		}
		
		return git_id;
	}

	//gitid 수정하기
	public int updateGiId(int user_code, String git_id) {
		String sql  = "update github set git_id= ? where user_code = ?";
		conn = MySQLUtil.getConnection();
		 
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, git_id);
			pst.setInt(2, user_code);
			
			result = pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, pst, conn);
		}
		
		return result;
	}
}
