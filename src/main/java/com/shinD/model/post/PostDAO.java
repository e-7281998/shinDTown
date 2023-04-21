package com.shinD.model.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinD.model.board.BoardVO;
import com.shinD.util.MySQLUtil;

public class PostDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 게시글 생성
	// 제목+내용 게시글
	public int SimplePostCreate(PostVO post) {
		int result = 0;
		String sql = "insert into POSTS(BOARD_CODE,USER_CODE,POST_TITLE,POST_CONTENT) values(?,?,?,?)";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post.getPOST_CODE());
			pst.setInt(2, post.getUSER_CODE());
			pst.setString(3, post.getPOST_TITLE());
			pst.setString(4, post.getPOST_CONTENT());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return result;
	}

	// 제목+내용+이미지 게시글
	public int ImagePostCreate(PostVO post) {
		int result = 0;
		String sql = "insert into POSTS(BOARD_CODE,USER_CODE,POST_TITLE,POST_CONTENT,POST_IMAGE) values(?,?,?,?,?)";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post.getPOST_CODE());
			pst.setInt(2, post.getUSER_CODE());
			pst.setString(3, post.getPOST_TITLE());
			pst.setString(4, post.getPOST_CONTENT());
			pst.setString(5, post.getPOST_IMAGE());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return result;
	}

	// 제목+내용+소스코드 게시물
	public int SourcePostCreate(PostVO post) {
		int result = 0;
		String sql = "insert into POSTS(BOARD_CODE,USER_CODE,POST_TITLE,POST_CONTENT,POST_SOURCE) values(?,?,?,?,?)";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post.getPOST_CODE());
			pst.setInt(2, post.getUSER_CODE());
			pst.setString(3, post.getPOST_TITLE());
			pst.setString(4, post.getPOST_CONTENT());
			pst.setString(5, post.getPOST_SOURCE());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return result;
	}

	// 제목+내용+이미지+소스코드 게시물
	public int AllPostCreate(PostVO post) {
		int result = 0;
		String sql = "insert into POSTS(BOARD_CODE,USER_CODE,POST_TITLE,POST_CONTENT,POST_IMAGE,POST_SOURCE) values(?,?,?,?,?,?)";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, post.getPOST_CODE());
			pst.setInt(2, post.getUSER_CODE());
			pst.setString(3, post.getPOST_TITLE());
			pst.setString(4, post.getPOST_CONTENT());
			pst.setString(5, post.getPOST_IMAGE());
			pst.setString(6, post.getPOST_SOURCE());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return result;
	}

	// 게시글 목록(게시판 번호로 검색)
	public List<PostVO> PostSelect(int board_code) {
		String sql = "select * from POSTS where board_code='" + board_code + "'";
		List<PostVO> postlist = new ArrayList<>();

		conn = MySQLUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				PostVO post = makepost(rs);
				System.out.println(post);
				postlist.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return postlist;
	}

	// 게시글목록만들기--위에서 받은 rs값으로 보드생성
	private PostVO makepost(ResultSet rs) throws SQLException {
		PostVO post = new PostVO();// VO에 맞게 보드생성
		post.setPOST_CODE(rs.getInt("POST_CODE"));
		post.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		post.setUSER_CODE(rs.getInt("USER_CODE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CONTENT(rs.getString("POST_CONTENT"));
		post.setPOST_IMAGE(rs.getString("POST_IMAGE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CREATE(rs.getDate("POST_CREATE"));

		return post;
	}

	// 게시글 목록(게시글 번호로 검색)
	public PostVO PostDetail(int post_code) {
		String sql = "select * from POSTS where post_code='" + post_code + "'";
		PostVO postdetail = new PostVO();

		conn = MySQLUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				postdetail.setPOST_CODE(rs.getInt("POST_CODE"));
				postdetail.setBOARD_CODE(rs.getInt("BOARD_CODE"));
				postdetail.setUSER_CODE(rs.getInt("USER_CODE"));
				postdetail.setPOST_TITLE(rs.getString("POST_TITLE"));
				postdetail.setPOST_CONTENT(rs.getString("POST_CONTENT"));
				postdetail.setPOST_IMAGE(rs.getString("POST_IMAGE"));
				postdetail.setPOST_TITLE(rs.getString("POST_TITLE"));
				postdetail.setPOST_CREATE(rs.getDate("POST_CREATE"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return postdetail;
	}
}
