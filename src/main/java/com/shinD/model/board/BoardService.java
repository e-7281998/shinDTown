package com.shinD.model.board;

import java.util.List;

import com.shinD.model.board.BoardDAO;
import com.shinD.model.board.BoardPostVO;
import com.shinD.model.board.BoardVO;

public class BoardService {
	BoardDAO dao = new BoardDAO(); 
	
	//게시판 생성
	public int BoardCreate(int user_code, String board_name) {
		return dao.BoardCreate(user_code,board_name);
	}
	
	//게시판 목록조회
	public List<BoardVO> boardAll(){
		return dao.boardAll();
	}
	// 게시판+게시글이 조금 합쳐진목록
		public List<BoardPostVO> boardPostAll() {
			return dao.boardPostAll();
		}
	
	//게시판 인기글 순서대로 가져오기
	public List<BoardVO> boardTop(){
		return dao.boardTop();
	}
	
	//게시판명 중복 체크
	public int boardCheck(String boardName) {
				return dao.boardCheck(boardName);
	}
	
	//게시판 삭제
	public int boardDelete(String boardName) {
		return dao.boardDelete(boardName);		
	}
	
	//게시판명 검색
	public List<String> boardSerch(String board_name) {
		return dao.boardSerch(board_name);
	}
	// 게시판명 검색(번호형)
	public int boardSerchCode(String board_name) {
		return dao.boardSerchCode(board_name);
	}
	// 게시판디테일 온로드될 떄 가져오기
	public List<BoardVO> boardLoad(String board_name) {
		return dao.boardLoad(board_name);
	}
}