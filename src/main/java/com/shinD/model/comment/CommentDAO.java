package com.shinD.model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shinD.model.post.LikeVO;
import com.shinD.util.MySQLUtil;

public class CommentDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	int result = 0;
	
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
	
	//댓글읽기
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
	
	//댓글 작성자 가져오기
		public String com_userName(int com_code){
			String user_name = null;
			String sql = "select user_name as name from users u join comments c on u.user_code = c.USER_CODE where COM_CODE = ?";
			conn = MySQLUtil.getConnection();
			
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, com_code);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					user_name = (String) rs.getString("name");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(null, pst, conn);
			}
			return user_name;
		}
	//댓글 좋아요했는지 확인
	public int CheckCom(int com_code, int user_code) {
		int result = 0;
		String sql = "select like_code from LIKES where COM_CODE = ? AND USER_CODE = ?";
		conn = MySQLUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_code);
			pst.setInt(2, user_code);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				result = rs.getInt("like_code");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("comm 좋아요 결과" + result);
		return result;
	}
	
	//좋아요 수 가져오기~
	public int Likes(int com_code) {
		int likes = 0;
		String sql = "select count(*) as count from likes where com_code = ?";
		conn = MySQLUtil.getConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_code);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				likes = rs.getInt("count");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
	
		return likes;
	}
	
	// 좋아요 달기
	public int LikeCreate(LikeVO like) {
		int result = 0;
		String sql="insert into likes(USER_CODE, COM_CODE) values(?,?)";
		conn = MySQLUtil.getConnection();
		
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, like.getUSER_CODE());
			pst.setInt(2, like.getCOM_CODE());
			result= pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		
		return result;
	}
	// 좋아요취소 
	public int DeleteLike(int com_code , int user_code) {
		String sql = "delete from likes where com_code = ? and user_code = ?";
		conn = MySQLUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_code);
			pst.setInt(2, user_code);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("like 삭제 결과" + result);
		return result;
	}
	// 댓글 삭제
	public int deleteComment(int com_code) {
		String sql = "delete from comments where com_code = ?";
		conn = MySQLUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_code);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("comm 삭제 결과" + result);
		return result;
	}
	//댓글 확인
	public int getcom(int post_code, int user_code, String com_comment) {
		int result = 0;
		String sql = "select max(com_code) as com_code from comments where post_code = ? AND USER_CODE = ? and com_comment = ?";
		conn = MySQLUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post_code);
			pst.setInt(2, user_code);
			pst.setString(3, com_comment);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				result = rs.getInt("com_code");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("comm의 번호 가져오기 결과" + result);
		return result;
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
