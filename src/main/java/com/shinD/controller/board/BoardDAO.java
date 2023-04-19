package com.shinD.controller.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shinD.util.MySQLUtil;
import com.shinD.controller.board.BoardVO;

public class BoardDAO {
		Connection conn;
		Statement st;
		PreparedStatement pst;
		ResultSet rs;
		
		//게시판 생성
		public int BoardCreate(BoardVO board) {
			int result = 0;
			String sql="insert into BOARDS(USER_CODE,BOARD_NAME) values(?,?)";
			conn = MySQLUtil.getConnection();
			try {
				pst=conn.prepareStatement(sql);
				pst.setInt(1,board.getUSER_CODE());
				pst.setString(2, board.getBOARD_NAME());
				result=pst.executeUpdate();//문장실행
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = -1;
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(null, pst, conn);
			}
			System.out.println(result);
			return result;
		}
		
		//게시판 목록
		public List<BoardVO> boardAll(){
			String sql = "select * from boards order by board_code";
			List<BoardVO> boardlist = new ArrayList<>();
			
			conn = MySQLUtil.getConnection();
			System.out.println("gdgd");
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
			
				while(rs.next()) {
					BoardVO board = makeBoard(rs);
					System.out.println(board);
					boardlist.add(board);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(rs, st, conn);
			}
			System.out.println(boardlist.get(0));
			
			return boardlist;
		}
		//게시판목록만들기
		private BoardVO makeBoard(ResultSet rs) throws SQLException {
			BoardVO board = new BoardVO();
			board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
			board.setUSER_CODE(rs.getInt("USER_CODE"));
			board.setBOARD_NAME(rs.getString("BOARD_NAME"));
		
			return board;
		}
		
		
		//게시판명 중복 체크
		public int boardCheck(String boardName) {
			int result=0;
			String sql = "select count(*) from boards where board_name ="+boardName;
			conn = MySQLUtil.getConnection();
			
			try {
				st= conn.createStatement();
				rs= st.executeQuery(sql);
				result=rs.getInt("count");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(rs, st, conn);
			}
			
			
			return result;
		}
		
		//게시판 삭제
		public int boardDelete(String boardName, int userCode) {
			int result=0;
			String sql="delete from boards where board_name = ? and USER_CODE = ?";
			conn = MySQLUtil.getConnection();
			
			try {
				//pst에 spl문 연결해주고 ? 변수에 값 저장
				pst = conn.prepareStatement(sql);
				pst.setString(1, boardName);
				pst.setInt(2,userCode);
				
				//pst를 실행시키고 성공시 1이상의 값 가져옴
				result = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result=-1;
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(null, pst, conn);
			}
			
			return result;
		}
}
