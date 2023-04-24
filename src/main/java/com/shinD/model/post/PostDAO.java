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
	//게시판 모든 정보받아 게시글 생성
	public int PostCreate(String board_name,int user_code,String post_title,String post_content,String post_image, String post_source) {
		int result = 0;
		String sql1="select * from boards where board_name='"+board_name+"'";//보드 이름으로 코드를 받아서
		String sql2="insert into POSTS(BOARD_CODE,USER_CODE,POST_TITLE,POST_CONTENT,POST_IMAGE,POST_SOURCE) values(?,?,?,?,?,?)";//게시판 생성
		conn = MySQLUtil.getConnection();
		int bcode=0;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
			while(rs.next()) {
			System.out.println(rs.getInt("BOARD_CODE"));
			bcode = rs.getInt("BOARD_CODE");//보드 코드 뽑아오기
			}
			MySQLUtil.dbDisconnect(rs, st, conn);
			
			conn = MySQLUtil.getConnection();
			pst = conn.prepareStatement(sql2);
			pst.setInt(1, bcode);
			pst.setInt(2, user_code);
			pst.setString(3, post_title);
			pst.setString(4, post_content);
			//이미지랑 코드 둘다 없으면
			if(post_image==(null) && post_source.equals("")) {
				pst.setString(5, null);
				pst.setString(6, null);
			}else if(post_image==null) {//이미지 없으면
				pst.setString(5, null);
				pst.setString(6, post_source);
			}else if(post_source.equals("")) {//코드 없으면
				pst.setString(5, post_image);
				pst.setString(6, null);
			}else {//전부 존재하면
			pst.setString(5, post_image);
			pst.setString(6, post_source);
			}
			result = pst.executeUpdate();//게시판 생성
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	//게시글+댓글 목록(게시판 번호로 검색)
	public List<PostCommentVO> PostComSelect(int board_code){
		List<PostCommentVO> postcommentlist = new ArrayList<>();
		String sql[] = new String[2];
		
		 sql[0] = "select board_code,posts.post_code,posts.user_code,post_title,post_content,post_image,post_source,post_create,count(*) as count from posts join comments \r\n"
				+ "on posts.post_code = comments.post_code group by board_code,posts.post_code,posts.user_code,post_title,post_content,post_image,post_source,post_create\r\n"
				+ "having board_code ='"+board_code+"'";
		
		 sql[1]="select count(likes.like) as LIKES\r\n"
				+ "from likes left outer join posts on posts.post_code = likes.post_code\r\n"
				+ "group by posts.post_code,likes.like\r\n"
				+ "having posts.post_code ='"+board_code+"'";
		conn = MySQLUtil.getConnection();

		try {
			for(int i =0;i<2;i++) {
			st = conn.createStatement();
			rs = st.executeQuery(sql[i]);
			while (rs.next()) {
				PostCommentVO post = makepostcomment(rs,i);
				postcommentlist.add(post);
			}
			
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}
		
		System.out.println(postcommentlist);
		return postcommentlist;
	}


	//게시글+댓글 목록 만들기---한번에 보여주려고
	private PostCommentVO makepostcomment(ResultSet rs, int i) throws SQLException {
		PostCommentVO post = new PostCommentVO();// VO에 맞게 보드생성
		if(i==0) {
		post.setPOST_CODE(rs.getInt("POST_CODE"));
		post.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		post.setUSER_CODE(rs.getInt("USER_CODE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CONTENT(rs.getString("POST_CONTENT"));
		post.setPOST_IMAGE(rs.getString("POST_IMAGE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CREATE(rs.getDate("POST_CREATE"));
		post.setCOUNT(rs.getInt("COUNT"));
		}else if(i==1)
			post.setLIKES(rs.getInt("LIKES"));

		return post;
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
//	public int PostCode() {
//		return post_code
//	}
}
