package com.shinD.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinD.util.MySQLUtil;

public class MemberDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	int result = 0;
	
	//회원 가입 
	public int registertUser(String userName, String userId,String userPwd, int userClass, String salt){
		String sql = "insert into users(USER_NAME,USER_ID,USER_PWD,USER_CLASS,USER_SALT) values(?,?,?,?,?)";
		conn = MySQLUtil.getConnection();
		
		System.out.println("옴");
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,userName);
			pst.setString(2,userId);
			pst.setString(3,userPwd);
			pst.setInt(4,userClass);
			pst.setString(5,salt);
			
			result = pst.executeUpdate();
			
			System.out.println("결과 : " +result);
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, pst, conn);
		}
		
		return result;
	}
	
	//로그인 - member 정보 얻기
	public MemberVO loginCheck(String id, String pwd) {
		MemberVO member = null;
		String sql = "select * from users where user_id=? and user_pwd=?";
		
		conn = MySQLUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			while(rs.next()) {
				member = new MemberVO();
				member.setUser_class(rs.getInt("USER_CLASS")); 
				member.setUser_code(rs.getInt("USER_CODE"));
				member.setUser_id(rs.getString("user_id"));
				member.setUser_name(rs.getString("USER_NAME"));;
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return member;
		
	}
	
	//id에 해당하는 salt 얻기
	public String getSalt(String id) {
		String sql = "select user_salt from users where user_id= ? ";
		String salt = "";
		conn = MySQLUtil.getConnection();

		System.out.println("salt 가지러 옴");
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				salt = rs.getString("user_salt");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return salt;
	}
}
