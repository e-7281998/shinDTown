package com.shinD.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.shinD.util.MySQLUtil;

public class MemberDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	int result = 0;
	
	//회원 가입 
	public List<MemberVO> registertUser(String userName, String userId,String userPwd, int useClass){
		String sql = "insert into users(USER_NAME,USER_ID,USER_PWD,USER_CLASS) values(?,?,?,?)";
		conn = MySQLUtil.getConnection();
		
		System.out.println("옴");
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,userName);
			pst.setString(2,userId);
			pst.setString(3,userName);
			pst.setInt(4,useClass);
			
			result = pst.executeUpdate();
			
			System.out.println("결과 : " +result);
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, pst, conn);
		}
		
		return null;
	}
}
