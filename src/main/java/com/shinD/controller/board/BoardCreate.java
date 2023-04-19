package com.shinD.controller.board;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;



public class BoardCreate implements CommonControllerInterface {
	
	@Override
	public String execute(Map<String, Object> data) throws Exception{
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String page = "BoardCreate.jsp";
		String method = (String)data.get("method");
		
		//메소드 형식 따라서
		if(method.equals("POST")) {
			//보드 중복체크
			BoardService bservice = new BoardService();
			int result = bservice.boardCheck(request.getParameter("board_name"));//1이면 중복 0이면 없는 게시판 명
	
			//보드 생성 게시판명이 유니크여서 오류가 뜨지만 뜨기전에 해결
			if(result == 1) {//게시판이 이미 존재
				System.out.println("해당 게시판명은 사용중입니다");
			}else {//게시판이 없다
				BoardVO board = makeBoard(request);//보드형식에 입력받은 값을 넣고 새로운 보드를 만든다
				bservice.BoardCreate(board);//위에서 만든 보드형식으로 새로운 보드 만들기
				System.out.println("생성 완료햇습니다.");
				bservice.boardAll();//테스트겸 보드 목록보기
			}
			page = "responseBody:게시판생성완료";
			
		}else {}
		
		return page;
	}

	//보드만들기
	private BoardVO makeBoard(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		//int board_code = Integer.parseInt(request.getParameter("board_code"));오토라서 주석처리함
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		String board_name = request.getParameter("board_name");
		
		BoardVO board = new BoardVO();
		//board.setBOARD_CODE(board_code);오토라서 주석처리함
		board.setUSER_CODE(user_code);
		board.setBOARD_NAME(board_name);
		
		
		return board;
	}
}
