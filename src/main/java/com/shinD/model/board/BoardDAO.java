package com.shinD.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shinD.model.board.BoardVO;
import com.shinD.util.MySQLUtil;

public class BoardDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	// 게시판 생성-- 정수 값 result를 리턴해 1 이상이면 생성가능
	public int BoardCreate(int user_code, String board_name) {
		int result = 0;
		String sql = "insert into BOARDS(USER_CODE,BOARD_NAME) values(?,?)";
		conn = MySQLUtil.getConnection();
		try {
			// ?로 값을 입력받기때문에 pst
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_code);// 1번 ?에 유저코드
			pst.setString(2, board_name);// 2번 ?에 보드이름
			result = pst.executeUpdate();// 문장실행
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = -1;// 오류나면 값-1
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println(result);
		return result;
	}

	// 게시판 목록
	public List<BoardVO> boardAll() {
		String sql = "select * from boards order by board_code";
		List<BoardVO> boardlist = new ArrayList<>();// 보드들을 저장할 리스트 생성

		conn = MySQLUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {// 값이 없을때까지 읽음
				BoardVO board = makeBoard(rs);// 보드를 만들어주는 함수에 다녀와서 보드생성
				boardlist.add(board);// 리스트에 보드 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return boardlist;
	}

	// 게시판+게시글이 조금 합쳐진목록
	public List<BoardPostVO> boardPostAll() {
		String sql = """
				select *
				from (
						select boards.*,post_code,post_title,
						ROW_NUMBER() OVER(PARTITION BY boards.board_code) cnt
						from boards join posts on posts.BOARD_CODE = boards.BOARD_CODE
						order by posts.post_code desc
				 ) aa
				where aa.cnt <= 3;
				""";
		List<BoardPostVO> boardpostlist = new ArrayList<>();// 보드들을 저장할 리스트 생성

		conn = MySQLUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {// 값이 없을때까지 읽음
				BoardPostVO board = makeBoardPost(rs);// 보드를 만들어주는 함수에 다녀와서 보드생성
				boardpostlist.add(board);// 리스트에 보드 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return boardpostlist;
	}

	// 게시판목록만들기--위에서 받은 rs값으로 보드생성
	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		BoardVO board = new BoardVO();// VO에 맞게 보드생성
		board.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		board.setUSER_CODE(rs.getInt("USER_CODE"));
		board.setBOARD_NAME(rs.getString("BOARD_NAME"));

		return board;
	}

	// 게시판+게시글 목록만들기--위에서 받은 rs값으로 보드생성
	private BoardPostVO makeBoardPost(ResultSet rs) throws SQLException {
		BoardPostVO boardpost = new BoardPostVO();// VO에 맞게 보드생성
		boardpost.setBOARD_CODE(rs.getInt("BOARD_CODE"));
		boardpost.setUSER_CODE(rs.getInt("USER_CODE"));
		boardpost.setBOARD_NAME(rs.getString("BOARD_NAME"));
		boardpost.setPOST_CODE(rs.getInt("POST_CODE"));
		boardpost.setPOST_TITLE(rs.getString("POST_TITLE"));

		return boardpost;
	}

	// 게시판 인기글 순서대로 가져오기
	public List<BoardVO> boardTop() {
		String sql = "select * from boards where board_code= any(select board_code from posts group by board_code having board_code>5 order by count(*) desc)";
		List<BoardVO> boardtop = new ArrayList<>();// 보드들을 저장할 리스트 생성

		conn = MySQLUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {// 값이 없을때까지 읽음
				BoardVO board = makeBoard(rs);// 보드를 만들어주는 함수에 다녀와서 보드생성
				boardtop.add(board);// 리스트에 보드 추가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return boardtop;
	}

	// 게시판명 중복 체크--result값이 1이면 중복된게 있다
	public int boardCheck(String board_name) {
		int result = 0;
		String sql = "select count(*) as cnt from boards where board_name = '" + board_name + "'";
		conn = MySQLUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				result = rs.getInt("cnt");// rs에서 cnt의 값을 가져온다.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}

		return result;
	}

	// 게시판 삭제--result 값이 1이상이면 성공
	// 유저코드와 보드이름이 일치해야 삭제가 가능하다.
	public int boardDelete(String boar_name, int user_code) {
		int result = 0;
		String sql = "delete from boards where board_name = ? and USER_CODE = ?";
		conn = MySQLUtil.getConnection();

		try {
			// pst에 spl문 연결해주고 ? 변수에 값 저장
			pst = conn.prepareStatement(sql);
			pst.setString(1, boar_name);// 첫번째 ? 에 보드이름
			pst.setInt(2, user_code);// 두번째 ?에 유저코드

			// pst를 실행시키고 성공시 1이상의 값 가져옴
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = -1;
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return result;
	}

	// 게시판명 검색해서 이름으로 받기
	public List<String> boardSerch(String board_name) {
		List<String> bnamelist = new ArrayList<>();
		String sql = "select board_name from boards where board_name like ?";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+board_name+"%");//단어가 들어가면 무조건 찾기
			rs = pst.executeQuery();

			while (rs.next()) {

				bnamelist.add(rs.getString("BOARD_NAME"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}

		return bnamelist;
	}
	
		// 게시판명 검색해서 번호로받기
		public int boardSerchCode(String board_name) {
			int bcode = 0;
			String sql = "select board_code from boards where board_name =?";
			conn = MySQLUtil.getConnection();

			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, board_name);
				rs = pst.executeQuery();

				while (rs.next()) {

					bcode = rs.getInt("board_code");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				MySQLUtil.dbDisconnect(null, pst, conn);
			}

			return bcode;
		}

}
