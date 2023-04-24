package com.shinD.model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shinD.util.MySQLUtil;

public class CommentDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	//댓글 생성
	public int CommentCreate(CommentVO comment) {
		int result = 0;
		String sql="insert into comments(post_code,user_code,com_comment) values(?,?,?)";
		conn = MySQLUtil.getConnection();
		
		try {
			System.out.println(comment.getPOST_CODE());
			System.out.println(comment.getUSER_CODE());
			pst=conn.prepareStatement(sql);
			pst.setInt(1, comment.getPOST_CODE());
			pst.setInt(2, comment.getUSER_CODE());
			pst.setString(3, comment.getCOM_COMMENT());
			result= pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		
		return result;
	}
	
	//댓글
	public List<CommentVO> ComList(int post_code){
		List<CommentVO> comlist = new ArrayList<>();
		String sql = "select * from comments where post_code = ?";
		conn = MySQLUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post_code);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				CommentVO com = makeCom(rs);
				comlist.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
	
		
		return comlist;
	}

	private CommentVO makeCom(ResultSet rs2) throws SQLException {
		CommentVO com = new CommentVO();
		com.setCOM_CODE(rs.getInt("COM_CODE"));
		com.setPOST_CODE(rs.getInt("POST_CODE"));
		com.setUSER_CODE(rs.getInt("USER_CODE"));
		com.setCOM_COMMENT(rs.getString("COM_COMMENT"));
		com.setCOM_CREATE(rs.getDate("COM_CREATE"));

		return com;
	}
	
}
