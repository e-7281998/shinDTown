package com.shinD.controller.board;

import java.util.List;

public class BoardService {
	BoardDAO dao = new BoardDAO(); 
	
	//게시판 생성
	public int BoardCreate(BoardVO board) {
		return dao.BoardCreate(board);
	}
	
	//게시판 목록조회
	public List<BoardVO> boardAll(){
		return dao.boardAll();
	}
	
	//게시판명 중복 체크
	public int boardCheck(String boardName) {
				return dao.boardCheck(boardName);
	}
	
	//게시판 삭제
	public int boardDelete(String boardName, int userCode) {
		return dao.boardDelete(boardName, userCode);		
	}
}
