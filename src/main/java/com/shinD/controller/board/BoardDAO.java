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
		
		//게시판 생성-- 정수 값 result를 리턴해 1 이상이면 생성가능
		public int BoardCreate(BoardVO board) {
			int result = 0;
			String sql="insert into BOARDS(USER_CODE,BOARD_NAME) values(?,?)";
			conn = MySQLUtil.getConnection();
			try {
				//?로 값을 입력받기때문에 pst 
				pst=conn.prepareStatement(sql);
				pst.setInt(1,board.getUSER_CODE());//1번 ?에 유저코드
				pst.setString(2, board.getBOARD_NAME());//2번 ?에 보드이름
				result=pst.executeUpdate();//문장실행
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = -1;//오류나면 값-1
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
			List<BoardVO> boardlist = new ArrayList<>();//보드들을 저장할 리스트 생성
			
			conn = MySQLUtil.getConnection();
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
			
				while(rs.next()) {//값이 없을때까지 읽음
					BoardVO board = makeBoard(rs);//보드를 만들어주는 함수에 다녀와서 보드생성
					System.out.println(board);//테스트하려고 붙여놓음 지워도댐
					boardlist.add(board);//리스트에 보드 추가
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(rs, st, conn);
			}
			
			return boardlist;
		}
		//게시판목록만들기--위에서 받은 rs값으로 보드생성
		private BoardVO makeBoard(ResultSet rs) throws SQLException {
			BoardVO board = new BoardVO();//VO에 맞게 보드생성
			board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
			board.setUSER_CODE(rs.getInt("USER_CODE"));
			board.setBOARD_NAME(rs.getString("BOARD_NAME"));
		
			return board;
		}
		
		
		//게시판명 중복 체크--result값이 1이면 중복된게 있다
		public int boardCheck(String board_name) {
			int result=0;
			String sql = "select count(*) as cnt from boards where board_name = '"+board_name+"'";
			conn = MySQLUtil.getConnection();
			
			try {
				st= conn.createStatement();
				rs= st.executeQuery(sql);
				while(rs.next()) {
				result=rs.getInt("cnt");//rs에서 cnt의 값을 가져온다.
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(rs, st, conn);
			}
			
			
			return result;
		}
		
		//게시판 삭제--result 값이 1이상이면 성공
		//유저코드와 보드이름이 일치해야 삭제가 가능하다.
		public int boardDelete(String boar_name, int user_code) {
			int result=0;
			String sql="delete from boards where board_name = ? and USER_CODE = ?";
			conn = MySQLUtil.getConnection();
			
			try {
				//pst에 spl문 연결해주고 ? 변수에 값 저장
				pst = conn.prepareStatement(sql);
				pst.setString(1, boar_name);//첫번째 ? 에 보드이름
				pst.setInt(2,user_code);//두번째 ?에 유저코드
				
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
