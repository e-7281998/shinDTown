package com.shinD.model.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinD.controller.post.PostLike;
import com.shinD.model.board.BoardVO;
import com.shinD.util.MySQLUtil;

public class PostDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	ResultSet rs2;
	ResultSet rs3;

	// 게시글 생성
	//게시판 모든 정보받아 게시글 생성
	public int PostCreate(String board_name,int user_code,String post_title,String post_content,String post_image, String post_source) {
		int result = 0;
		//게시글 제목이 널일
		if(post_title.equals("")) {
			return result;
		}else {
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
		}
		return result;
	}
	
	//게시글 삭제
	public int PostDelete(int post_code) {
		int result=0;
		String sql="delete from posts where post_code = ?";
		conn=MySQLUtil.getConnection();
		
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, post_code);
			result = pst.executeUpdate();	
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
	//게시글+댓글+좋아요 목록(게시판 번호로 검색)
	public List<PostCommentVO> PostComSelect(int board_code){
		List<PostCommentVO> postcommentlist = new ArrayList<>();
		
		String sql1="select p.*,USER_NAME from users u join posts p on u.user_code = p.user_code where board_code ='"+board_code+"'";
		String sql2="select p.post_code,count(p.post_code) as POST_COMS from posts p join comments c on p.post_code = c.post_code "
				+ "group by p.post_code,p.board_code having p.board_code ='"+board_code+"'";
		String sql3="select p.post_code,count(l.likey) as POST_LIKES from posts p join likes l on p.post_code = l.post_code "
				+ "group by p.post_code,l.post_code,p.board_code having p.board_code ='"+board_code+"'";
		
		conn = MySQLUtil.getConnection();

		try {
		
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
		
			//먼저 리스트를 생성
			while(rs.next()) {
				PostCommentVO post = makepostcomment(rs);
				postcommentlist.add(post);
			}
			MySQLUtil.dbDisconnect(rs, st, conn);
			
			conn = MySQLUtil.getConnection();
			st = conn.createStatement();
			rs2= st.executeQuery(sql2);
		
			//생성한 리스트에서 같은 post_code값이 있는 것이 있으면 set해서 다시 만들어줌
			while(rs2.next()) {
				System.out.println(rs2.getInt("p.post_code"));
				System.out.println(rs2.getInt("POST_COMS"));;
				for(int i=0;i<postcommentlist.size();i++) {
					if(postcommentlist.get(i).getPOST_CODE()==rs2.getInt("p.post_code")) { 
						makepostcomments(postcommentlist.get(i),rs2); 
					}
				}
			}
			MySQLUtil.dbDisconnect(rs, st, conn);
			
			conn = MySQLUtil.getConnection();
			st = conn.createStatement();
			rs3 = st.executeQuery(sql3);
			while(rs3.next()) {
				for(int i=0;i<postcommentlist.size();i++) {
					if(postcommentlist.get(i).getPOST_CODE()==rs3.getInt("p.post_code")) { 
						makepostcommentslikes(postcommentlist.get(i),rs3); 
					}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
			MySQLUtil.dbDisconnect(rs2, st, conn);
			MySQLUtil.dbDisconnect(rs3, st, conn);
		
		}
		
		return postcommentlist;
	}

	//좋아요 개수 추가
	private void makepostcommentslikes(PostCommentVO postc, ResultSet rs3) throws SQLException {
		postc.setPOST_CODE(postc.getPOST_CODE());
		postc.setBOARD_CODE(postc.getBOARD_CODE());
		postc.setUSER_CODE(postc.getUSER_CODE());
		postc.setPOST_TITLE(postc.getPOST_TITLE());
		postc.setPOST_CONTENT(postc.getPOST_CONTENT());
		postc.setPOST_IMAGE(postc.getPOST_IMAGE());
		postc.setPOST_SOURCE(postc.getPOST_SOURCE());
		postc.setPOST_CREATE(postc.getPOST_CREATE());
		postc.setUSER_NAME(postc.getUSER_NAME());
		postc.setPOST_COMS(postc.getPOST_COMS());
		postc.setPOST_LIKES(rs3.getInt("POST_LIKES"));

		System.out.println(postc);
		
	}


	//댓글개수추가
	private void makepostcomments(PostCommentVO postc, ResultSet rs2) throws SQLException {
		postc.setPOST_CODE(postc.getPOST_CODE());
		postc.setBOARD_CODE(postc.getBOARD_CODE());
		postc.setUSER_CODE(postc.getUSER_CODE());
		postc.setPOST_TITLE(postc.getPOST_TITLE());
		postc.setPOST_CONTENT(postc.getPOST_CONTENT());
		postc.setPOST_IMAGE(postc.getPOST_IMAGE());
		postc.setPOST_SOURCE(postc.getPOST_SOURCE());
		postc.setPOST_CREATE(postc.getPOST_CREATE());
		postc.setUSER_NAME(postc.getUSER_NAME());
		postc.setPOST_COMS(rs2.getInt("POST_COMS"));
		postc.setPOST_LIKES(0);

		System.out.println(postc);
	}


	//게시글+댓글+좋아요 목록 만들기---한번에 보여주려고
	private PostCommentVO makepostcomment(ResultSet rs) throws SQLException {
		PostCommentVO post = new PostCommentVO();// VO에 맞게 보드생성
	
		post.setPOST_CODE(rs.getInt("POST_CODE"));
		post.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		post.setUSER_CODE(rs.getInt("USER_CODE"));
		if(rs.getInt("USER_CODE")==0) {
			post.setUSER_CODE(0);
		}
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CONTENT(rs.getString("POST_CONTENT"));
		post.setPOST_IMAGE(rs.getString("POST_IMAGE"));
		post.setPOST_SOURCE(rs.getString("POST_SOURCE"));
		post.setPOST_CREATE(rs.getDate("POST_CREATE"));
		post.setUSER_NAME(rs.getString("USER_NAME"));
		post.setPOST_COMS(0);
		post.setPOST_LIKES(0);

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
	
	//인기게시글 순서대로 가져오기
	public List<PostCommentVO> PostTop(){
		List<PostCommentVO> posttoplist = new ArrayList<>();
		String sql = "select p.post_code,p.board_code,p.user_code,p.post_title,p.post_content,p.post_image,p.post_source,p.post_create,count(l.post_code) as POST_LIKES\r\n"
				+ "from posts p join likes l on p.post_code = l.post_code\r\n"
				+ "group by p.post_code,p.board_code,p.user_code,p.post_title,p.post_content,p.post_image,p.post_source,p.post_create,l.post_code\r\n"
				+ "order by count(l.post_code) desc";
		conn=MySQLUtil.getConnection();
		
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
		
			while(rs.next()) {
				PostCommentVO post = makePostTop(rs);
				posttoplist.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}
		
		
		return posttoplist;
	}

	//인기순 게시글만들기
	private PostCommentVO makePostTop(ResultSet rs) throws SQLException {
		PostCommentVO post = new PostCommentVO();// VO에 맞게 보드생성
		
		post.setPOST_CODE(rs.getInt("POST_CODE"));
		post.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		post.setUSER_CODE(rs.getInt("USER_CODE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CONTENT(rs.getString("POST_CONTENT"));
		post.setPOST_IMAGE(rs.getString("POST_IMAGE"));
		post.setPOST_TITLE(rs.getString("POST_TITLE"));
		post.setPOST_CREATE(rs.getDate("POST_CREATE"));
		post.setPOST_COMS(0);
		post.setPOST_LIKES(rs.getInt("POST_LIKES"));

		return post;
	}
	
	//게시글 좋아요
	public int postLike(int user_code,int post_code) {
		int result = 0;
		String sql1="select count(*) as num from likes where user_code = ? and post_code = ?";
		String sql2 = "insert into LIKES(USER_CODE,POST_CODE) values(?,?)";
		conn = MySQLUtil.getConnection();
		
		try {
			pst=conn.prepareStatement(sql1);
			pst.setInt(1, user_code);
			pst.setInt(2, post_code);
			
			rs = pst.executeQuery();
			while(rs.next()) {
			if(rs.getInt("num")!=1) {
				Connection conn2 = MySQLUtil.getConnection();
				
				PreparedStatement pst2=conn2.prepareStatement(sql2);
				pst2.setInt(1, user_code);
				pst2.setInt(2, post_code);
				
				result = pst2.executeUpdate();

				MySQLUtil.dbDisconnect(null, pst2, conn2);
			}else {
				return result;
			}
			
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		
		return result;
	}
	
	//게시글 좋아요했는지 확인
		public int CheckPost(int post_code, int user_code) {
			int result = 0;
			String sql = "select like_code from LIKES where POST_CODE = ? AND USER_CODE = ?";
			conn = MySQLUtil.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, post_code);
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
			System.out.println("ㅉ끼는당");
			System.out.println("post 좋아요 결과" + result);
			return result;
		}
	//게시글 좋아요 삭제
		public int postLikeDel(int post_code) {
			int result = 0;
			String sql = "delete from likes where post_code = ?"; 
			conn=MySQLUtil.getConnection();
			
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, post_code);
				result = pst.executeUpdate();
				
			} catch (SQLException e) {
				result = -1;
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(null, pst, conn);
			}
			return result;
		}
}